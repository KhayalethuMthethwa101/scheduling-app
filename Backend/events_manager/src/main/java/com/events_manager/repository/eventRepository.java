package com.events_manager.repository;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import com.events_manager.model.*;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class eventRepository {
    private final Firestore firestore = FirestoreClient.getFirestore();

    //Save or Create event
    public String saveEvent(event event) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection("events").document(event.getEventId());
        ApiFuture<WriteResult> future = docRef.set(event);
        return future.get().getUpdateTime().toString();
    }

    //Retrieve event by Id
    public event getEventById(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection("events").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            return document.toObject(event.class);
        } else {
            return null;
        }
    }

    //Delete event by Id
    public String deleteEvent(String id) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> writeResult = firestore.collection("events").document(id).delete();
        return writeResult.get().getUpdateTime().toString();
    }

    //Fetch All Events
    public List<event> getAllEvents() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = firestore.collection("events").get();
        List<event> eventList = new ArrayList<>();

        for (DocumentSnapshot document : future.get().getDocuments()) {
            eventList.add(document.toObject(event.class));
        }
        return eventList;
    }
}