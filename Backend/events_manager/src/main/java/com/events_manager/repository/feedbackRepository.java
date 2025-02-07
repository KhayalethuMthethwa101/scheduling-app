package com.events_manager.repository;
import com.google.cloud.firestore.Firestore;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ExecutionException;
import com.events_manager.model.feedback;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import java.util.ArrayList;

@Repository
public class feedbackRepository {

    private final Firestore firestore;

    public feedbackRepository(Firestore firestore) {
        this.firestore = firestore;
    }

    // Save Feedback
    public String saveFeedback(feedback feedback) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection("feedbacks").document(feedback.getFeedbackId());
        ApiFuture<WriteResult> future = docRef.set(feedback);
        return future.get().getUpdateTime().toString();
    }

    // Retrieve Feedback by ID
    public feedback getFeedbackById(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection("feedbacks").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            return document.toObject(feedback.class);
        } else {
            return null;
        }
    }

    // Retrieve All Feedback for an Event
    public List<feedback> getFeedbackByEventId(String eventId) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = firestore.collection("feedbacks")
                .whereEqualTo("eventId", eventId).get();
        List<feedback> feedbackList = new ArrayList<>();

        for (DocumentSnapshot document : future.get().getDocuments()) {
            feedbackList.add(document.toObject(feedback.class));
        }
        return feedbackList;
    }
}
