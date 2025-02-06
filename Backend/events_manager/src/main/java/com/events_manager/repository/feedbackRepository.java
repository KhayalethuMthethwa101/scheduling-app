package com.events_manager.repository;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import com.events_manager.model.feedback;

@Repository
public class feedbackRepository {
    private final Firestore db = FirestoreClient.getFirestore();

    public void save(feedback feedbackDto) {
        db.collection("feedback").document(feedbackDto.getFeedbackId()).set(feedbackDto);
    }

    public List<String> findCommentsByEventId(String eventId) {
        return Collections.singletonList("");
    }
}