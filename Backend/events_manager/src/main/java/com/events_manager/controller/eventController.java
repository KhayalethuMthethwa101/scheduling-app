package com.events_manager.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.events_manager.service.eventService;
import com.events_manager.model.event;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/v1/events")
public class eventController {
    private final eventService eventService;

    @Autowired
    public eventController(eventService eventService) {
        this.eventService = eventService;
    }

    // Get all events
    @GetMapping
    public ResponseEntity<List<event>> getAllEvents() throws ExecutionException, InterruptedException {
        List<event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    // Get an event by ID
    @GetMapping("/{eventId}")
    public ResponseEntity<event> getEventById(@PathVariable String eventId) throws ExecutionException, InterruptedException {
        event event = eventService.getEvent(eventId);
        return event != null ? ResponseEntity.ok(event) : ResponseEntity.notFound().build();
    }

    // Delete an event
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable String id) throws ExecutionException, InterruptedException {
        String result = eventService.deleteEvent(id);
        return ResponseEntity.ok("Event deleted successfully at: " + result);
    }

    // Create an event (text data only, no image)
    @PostMapping("/addEvent")
    public ResponseEntity<event> createEvent(@RequestBody event event) throws ExecutionException, InterruptedException {
        event createdEvent = eventService.createEvent(event);
        return ResponseEntity.ok(createdEvent);
    }

    // Create an event with an image
    @PostMapping("/create")
    public ResponseEntity<event> createEventWithImage(
            @RequestPart("event") event event,
            @RequestPart("file") MultipartFile file
    ) throws Exception {
        String imageUrl = eventService.uploadImage(file);
        event.setImageUrl(imageUrl);
        event createdEvent = eventService.createEvent(event);
        return ResponseEntity.ok(createdEvent);
    }
}