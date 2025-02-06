package com.events_manager.repository;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;
import java.util.concurrent.ExecutionException;
import com.events_manager.model.*;

@Repository
public class eventRepository {
    private final Firestore db = FirestoreClient.getFirestore();

    public void save(event eventDto) {
        db.collection("events").document(eventDto.getEventId()).set(eventDto);
    }

    public event findById(String eventId) throws ExecutionException, InterruptedException {
        return db.collection("events").document(eventId).get().get().toObject(event.class);
    }

    public void update(Long eventId, event eventDto) {
    }
}