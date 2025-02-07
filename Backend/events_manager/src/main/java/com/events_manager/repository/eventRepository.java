package com.events_manager.repository;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import com.events_manager.model.*;

@Repository
public class eventRepository {
    private final Firestore firestore;

    @Autowired
    public eventRepository(Firestore firestore){
        this.firestore=firestore;
    }

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

    // Update Event Status
    public void updateEventStatus(String eventId, String status) throws ExecutionException, InterruptedException {
        firestore.collection("events").document(eventId).update("status", status).get();
    }

    // Mark Attendee as Attended
    public void addAttendee(String eventId, String visitorId) throws ExecutionException, InterruptedException {
        DocumentReference docRef = firestore.collection("events").document(eventId);
        docRef.update("attendees", FieldValue.arrayUnion(visitorId)).get();
    }
}