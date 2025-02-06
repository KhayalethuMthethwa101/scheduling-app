package com.events_manager.service;
import org.springframework.stereotype.Service;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.CollectionReference;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class eventService {
    @Autowired
    private Firestore firestore;

    public String createEvent(EventDto eventDto) throws ExecutionException, InterruptedException {
        firestore.collection("events").document(eventDto.getId()).set(eventDto).get();
        return "Event created successfully!";
    }

    public String updateEvent(Long eventId, EventDto eventDto) throws ExecutionException, InterruptedException {
        firestore.collection("events").document(String.valueOf(eventId)).set(eventDto).get();
        return "Event updated successfully!";
    }
}