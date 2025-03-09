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

    public List<feedback> getAllFeedback() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = firestore.collection("feedbacks").get();
        List<feedback> feedbackList = new ArrayList<>();

        for (DocumentSnapshot document : future.get().getDocuments()) {
            feedbackList.add(document.toObject(feedback.class));
        }
        return feedbackList;
    }
    public String addFeedback(feedback feedback) throws ExecutionException, InterruptedException {
        // Save feedback
        DocumentReference docRef = firestore.collection("feedbacks").document(feedback.getFeedbackId());
        ApiFuture<WriteResult> future = docRef.set(feedback);
        return future.get().getUpdateTime().toString();
    }

    public feedback getFeedback(String feedbackId) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection("feedbacks").document(feedbackId);
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
