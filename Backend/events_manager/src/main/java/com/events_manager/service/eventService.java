package com.events_manager.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.storage.*;
import com.events_manager.model.event;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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