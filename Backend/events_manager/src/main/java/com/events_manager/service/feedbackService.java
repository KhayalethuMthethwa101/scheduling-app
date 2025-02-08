package com.events_manager.service;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import com.events_manager.model.*;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class feedbackService {
    Firestore firestore = FirestoreClient.getFirestore();

    public String addFeedback(String eventId, String visitorId, int rating, int recommendation, String comment) throws ExecutionException, InterruptedException {
        // Save feedback
        String feedbackId = UUID.randomUUID().toString();
        feedback feedback = new feedback(feedbackId, eventId, visitorId, rating, recommendation, comment);
        DocumentReference docRef = firestore.collection("feedbacks").document(feedback.getFeedbackId());
        ApiFuture<WriteResult> future = docRef.set(feedback);
        return future.get().getUpdateTime().toString();
    }

    public feedback getFeedback(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection("feedbacks").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            return document.toObject(feedback.class);
        } else {
            return null;
        }
    }

    public List<feedback> getFeedbackByEvent(String eventId) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = firestore.collection("feedbacks")
                .whereEqualTo("eventId", eventId).get();
        List<feedback> feedbackList = new ArrayList<>();

        for (DocumentSnapshot document : future.get().getDocuments()) {
            feedbackList.add(document.toObject(feedback.class));
        }
        return feedbackList;
    }
}
