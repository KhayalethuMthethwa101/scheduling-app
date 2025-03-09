package com.events_manager.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.storage.*;
import com.events_manager.model.event;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class eventService {
    Firestore firestore = FirestoreClient.getFirestore();
    private final Storage storage = StorageOptions.getDefaultInstance().getService();
    private static final String BUCKET_NAME = "voting-app-b302c.appspot.com";

    public event createEvent(event event) throws ExecutionException, InterruptedException {
        WriteResult result = firestore.collection("events").document(event.getEventId()).set(event).get();
        return event;
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

    public String updateEvent(String eventId, event updatedEvent) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("events").document(eventId);

        // Fetch existing event
        event existingEvent = documentReference.get().get().toObject(event.class);

        if (existingEvent == null) {
            return "Event not found!";
        }

        // Convert both existing and updated events to a Map
        Map<String, Object> updatedFields = new HashMap<>();
        updatedFields.put("eventName", updatedEvent.getEventName() != null ? updatedEvent.getEventName() : existingEvent.getEventName());
        updatedFields.put("status", updatedEvent.getStatus() != null ? updatedEvent.getStatus() : existingEvent.getStatus());
        updatedFields.put("eventDescription", updatedEvent.getEventDescription() != null ? updatedEvent.getEventDescription() : existingEvent.getEventDescription());
        updatedFields.put("fee", updatedEvent.getFee() != null ? updatedEvent.getFee() : existingEvent.getFee());
        updatedFields.put("dateOfEvent", updatedEvent.getDateOfEvent() != null ? updatedEvent.getDateOfEvent() : existingEvent.getDateOfEvent());
        updatedFields.put("timeOfEvent", updatedEvent.getTimeOfEvent() != null ? updatedEvent.getTimeOfEvent() : existingEvent.getTimeOfEvent());
        updatedFields.put("imageUrl", updatedEvent.getImageUrl() != null ? updatedEvent.getImageUrl() : existingEvent.getImageUrl());
        updatedFields.put("location", updatedEvent.getLocation() != null ? updatedEvent.getLocation() : existingEvent.getLocation());
        updatedFields.put("latitude", updatedEvent.getLatitude() != null ? updatedEvent.getLatitude() : existingEvent.getLatitude());
        updatedFields.put("longitude", updatedEvent.getLongitude() != null ? updatedEvent.getLongitude() : existingEvent.getLongitude());
        updatedFields.put("capacity", updatedEvent.getCapacity() != 0 ? updatedEvent.getCapacity() : existingEvent.getCapacity());

        // Update only the changed fields
        documentReference.update(updatedFields);

        return "Event updated successfully!";
    }

    public String uploadImage(MultipartFile file) throws Exception {
        String fileName = "events/" + UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        BlobId blobId = BlobId.of(BUCKET_NAME, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(file.getContentType()).build();

        try (InputStream inputStream = file.getInputStream()) {
            storage.create(blobInfo, inputStream);
        }

        // Generate the public URL for the uploaded image
        return String.format("https://storage.googleapis.com/%s/%s", BUCKET_NAME, fileName);
    }
}