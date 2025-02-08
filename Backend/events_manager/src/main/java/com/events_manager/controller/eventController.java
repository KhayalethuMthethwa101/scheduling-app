package com.events_manager.controller;
import com.events_manager.service.bookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.events_manager.service.eventService;
import com.events_manager.model.event;

import com.google.cloud.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/v1/events")
public class eventController {
    private final eventService eventService;

    @Autowired
    public eventController(eventService eventService) {
        this.eventService=eventService;
    }

    @GetMapping
    public List<event> getAllEvents() throws ExecutionException, InterruptedException {
        return eventService.getAllEvents();
    }

    @PostMapping
    public String createEvent(@RequestParam String eventName, String evenDescription, String location, String status, Timestamp dateOfEvent) throws ExecutionException, InterruptedException {
        String eventId = UUID.randomUUID().toString();
        event event = new event(eventId, eventName, evenDescription, location, status, dateOfEvent);
        return eventService.createEvent(event);
    }

    @GetMapping("{eventId}")
    public event getEventById(@PathVariable String eventId) throws ExecutionException, InterruptedException {
        return eventService.getEvent(eventId);
    }

    @DeleteMapping("/{id}")
    public String deleteEvent(@PathVariable String id) throws ExecutionException, InterruptedException {
        return eventService.deleteEvent(id);
    }

    // Update Event Status
    @PostMapping("/{eventId}/update-status")
    public void updateEventStatus(@PathVariable String eventId) throws ExecutionException, InterruptedException {
        eventService.updateEventStatus(eventId);
    }

    // Mark Visitor as Attended
    @PostMapping("/{eventId}&{userId}")
    public void addAttendee(@PathVariable String eventId, String userId) throws ExecutionException, InterruptedException {
        eventService.addAttendee(eventId, userId);
    }
}