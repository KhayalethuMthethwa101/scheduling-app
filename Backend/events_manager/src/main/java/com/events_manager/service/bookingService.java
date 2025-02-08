package com.events_manager.service;
import com.events_manager.model.*;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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

    public String deleteBooking(String id) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> writeResult = firestore.collection("bookings").document(id).delete();
        return writeResult.get().getUpdateTime().toString();
    }

    public List<booking> getAllBookings() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = firestore.collection("bookings").get();
        List<booking> bookingList = new ArrayList<>();

        for (DocumentSnapshot document : future.get().getDocuments()) {
            bookingList.add(document.toObject(booking.class));
        }
        return bookingList;
    }
}
