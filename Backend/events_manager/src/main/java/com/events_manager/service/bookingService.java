package com.events_manager.service;
import com.events_manager.model.*;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class bookingService {
    Firestore firestore = FirestoreClient.getFirestore();

    public String addBooking(booking booking) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection("bookings").document(booking.getBookingId());
        ApiFuture<WriteResult> future = docRef.set(booking);
        return future.get().getUpdateTime().toString();
    }

    public booking getBooking(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection("bookings").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            return document.toObject(booking.class);
        } else {
            return null;
        }
    }

    public void deleteBooking(String bookingId) throws ExecutionException, InterruptedException {
        firestore.collection("bookings").document(bookingId).delete();
    }

    public List<booking> getAllBookings() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = firestore.collection("bookings").get();
        List<booking> bookingList = new ArrayList<>();

        for (DocumentSnapshot document : future.get().getDocuments()) {
            bookingList.add(document.toObject(booking.class));
        }
        return bookingList;
    }

    public List<booking> getBookingsByEmail(String email) throws ExecutionException, InterruptedException {
        CollectionReference bookings = firestore.collection("bookings");
        ApiFuture<QuerySnapshot> querySnapshot = bookings.whereEqualTo("email", email).get();

        List<booking> bookingList = new ArrayList<>();
        for (QueryDocumentSnapshot document : querySnapshot.get().getDocuments()) {
            bookingList.add(document.toObject(booking.class));
        }
        return bookingList;
    }

    public List<Map<String, Object>> getEventBookings() {
        try {
            QuerySnapshot querySnapshot = firestore.collection("bookings").get().get();
            return querySnapshot.getDocuments().stream()
                    .filter(doc -> doc.getString("eventId") != null) // Add null check
                    .collect(Collectors.groupingBy(doc -> doc.getString("eventId"), Collectors.counting()))
                    .entrySet().stream()
                    .map(entry -> {
                        Map<String, Object> map = new HashMap<>();
                        String eventId = entry.getKey();
                        map.put("eventId", eventId);
                        try {
                            map.put("eventName", firestore.collection("events").document(eventId).get().get().getString("eventName")); // Update this line
                        } catch (InterruptedException | ExecutionException e) {
                            throw new RuntimeException(e);
                        }
                        map.put("bookingCount", entry.getValue());
                        return map;
                    })
                    .collect(Collectors.toList());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Error fetching event bookings", e);
        }
    }

    public List<Map<String, Object>> getGenderDistribution() {
        try {
            QuerySnapshot querySnapshot = firestore.collection("users").get().get();
            return querySnapshot.getDocuments().stream()
                    .filter(doc -> doc.getString("gender") != null) // Add null check
                    .collect(Collectors.groupingBy(doc -> doc.getString("gender"), Collectors.counting()))
                    .entrySet().stream()
                    .map(entry -> {
                        Map<String, Object> map = new HashMap<>();
                        map.put("name", entry.getKey());
                        map.put("value", entry.getValue());
                        return map;
                    })
                    .collect(Collectors.toList());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Error fetching gender distribution", e);
        }
    }

    public List<Map<String, Object>> getAgeDistribution() {
        try {
            QuerySnapshot querySnapshot = firestore.collection("users").get().get();
            return querySnapshot.getDocuments().stream()
                    .filter(doc -> doc.getString("dateOfBirth") != null) // Add null check
                    .collect(Collectors.groupingBy(doc -> getAgeGroup(doc.getString("dateOfBirth")), Collectors.counting()))
                    .entrySet().stream()
                    .map(entry -> {
                        Map<String, Object> map = new HashMap<>();
                        map.put("name", entry.getKey());
                        map.put("value", entry.getValue());
                        return map;
                    })
                    .collect(Collectors.toList());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Error fetching age distribution", e);
        }
    }

    private String getAgeGroup(String dateOfBirth) {
        int age = calculateAge(dateOfBirth);
        if (age >= 0 && age <= 17) {
            return "0-17";
        } else if (age >= 18 && age <= 25) {
            return "18-25";
        } else if (age >= 26 && age <= 35) {
            return "26-35";
        } else if (age >= 36 && age <= 45) {
            return "36-45";
        } else if (age >= 46 && age <= 60) {
            return "46-60";
        } else if (age > 60) {
            return "60+";
        } else {
            return "Unknown"; // Handle invalid ages
        }
    }

    private int calculateAge(String dateOfBirth) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Assuming the date format is "yyyy-MM-dd"
        try {
            LocalDate dob = LocalDate.parse(dateOfBirth, formatter);
            return Period.between(dob, LocalDate.now()).getYears();
        } catch (DateTimeParseException e) {
            // Handle invalid date format
            return -1; // or any other default value or exception handling
        }
    }
}
