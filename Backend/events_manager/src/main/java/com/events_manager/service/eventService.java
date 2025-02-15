package com.events_manager.service;
import com.events_manager.util.TimestampConverter;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import com.events_manager.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class eventService {
    Firestore firestore = FirestoreClient.getFirestore();

    public String createEvent(event event) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection("events").document(event.getEventId());
        ApiFuture<WriteResult> future = docRef.set(event);
        return future.get().getUpdateTime().toString();
    }

    public event getEvent(String eventId) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection("events").document(eventId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            return document.toObject(event.class);
        } else {
            return null;
        }
    }

    public String deleteEvent(String id) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> writeResult = firestore.collection("events").document(id).delete();
        return writeResult.get().getUpdateTime().toString();
    }

    // Fetch All Events
    public List<event> getAllEvents() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = firestore.collection("events").get();
        List<event> eventList = new ArrayList<>();

        for (DocumentSnapshot document : future.get().getDocuments()) {
            eventList.add(document.toObject(event.class));
        }
        return eventList;
    }

//    // ✅ Check and update event status
//    public void updateEventStatus(String eventId) throws ExecutionException, InterruptedException {
//        DocumentReference docRef = firestore.collection("events").document(eventId);
//        ApiFuture<DocumentSnapshot> future = docRef.get();
//        DocumentSnapshot document = future.get();
//
//        if (document.exists()) {
//            event event = document.toObject(event.class);
//
//            if (event != null) {
//                LocalDateTime eventDateTime = TimestampConverter.convertToLocalDateTime(event.getDateOfEvent());
//                LocalDateTime now = LocalDateTime.now();
//
//                if (now.isBefore(eventDateTime)) {
//                    firestore.collection("events").document(eventId)
//                            .update("status", "COMING");
//                } else if (now.isAfter(eventDateTime) && now.isBefore(eventDateTime.plusHours(24))) {
//                    firestore.collection("events").document(eventId)
//                            .update("status", "ACTIVE");
//                } else {
//                    firestore.collection("events").document(eventId)
//                            .update("status", "ENDED");
//                }
//            }
//        }else {
//            throw new IllegalArgumentException("Event not found.");
//        }
//    }

    public void addAttendee(String eventId, String visitorId) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection("events").document(eventId);
        docRef.update("attendees", FieldValue.arrayUnion(visitorId)).get();
    }
}