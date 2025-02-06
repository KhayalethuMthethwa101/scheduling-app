package com.events_manager.service;
import org.springframework.stereotype.Service;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.CollectionReference;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class bookingService {
    @Autowired
    private Firestore firestore;

    public String rsvpForEvent(Long eventId, Long visitorId) throws ExecutionException, InterruptedException {
        firestore.collection("bookings").document().set(new BookingDto(eventId, visitorId)).get();
        return "RSVP successful!";
    }

    public List<String> getEventAttendees(Long eventId) throws ExecutionException, InterruptedException {
        return firestore.collection("bookings").whereEqualTo("eventId", eventId).get().get().getDocuments()
                .stream().map(doc -> doc.getString("visitorName")).collect(Collectors.toList());
    }
}