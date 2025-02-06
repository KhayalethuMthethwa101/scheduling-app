package com.events_manager.repository;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;
import com.events_manager.model.*;

import java.util.Collections;
import java.util.List;

@Repository
public class bookingRepository {
    private final Firestore db = FirestoreClient.getFirestore();

    public void save(booking bookingDto) {
        db.collection("bookings").document(bookingDto.getBookingId()).set(bookingDto);
    }

    public List<String> findAttendeesByEventId(String eventId) {
        return Collections.singletonList("");
    }
}
