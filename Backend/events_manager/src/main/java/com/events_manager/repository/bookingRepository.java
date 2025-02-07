package com.events_manager.repository;
import com.google.cloud.firestore.Firestore;
import org.springframework.stereotype.Repository;
import com.events_manager.model.*;

import java.util.List;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@Repository
public class bookingRepository {

    private final Firestore firestore;

    public bookingRepository(Firestore firestore) {
        this.firestore = firestore;
    }

    // Save Booking
    public String saveBooking(booking booking) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection("bookings").document(booking.getBookingId());
        ApiFuture<WriteResult> future = docRef.set(booking);
        return future.get().getUpdateTime().toString();
    }

    // Retrieve Booking by ID
    public booking getBookingById(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection("bookings").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            return document.toObject(booking.class);
        } else {
            return null;
        }
    }

    // Delete Booking
    public String deleteBooking(String id) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> writeResult = firestore.collection("bookings").document(id).delete();
        return writeResult.get().getUpdateTime().toString();
    }

    // Retrieve All Bookings
    public List<booking> getAllBookings() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = firestore.collection("bookings").get();
        List<booking> bookingList = new ArrayList<>();

        for (DocumentSnapshot document : future.get().getDocuments()) {
            bookingList.add(document.toObject(booking.class));
        }
        return bookingList;
    }
}
