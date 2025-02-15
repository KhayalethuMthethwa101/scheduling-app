package com.events_manager.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.events_manager.service.eventService;
import com.events_manager.model.event;

import java.util.List;
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

    @PostMapping("/addEvent")
    public void createEvent(@RequestBody event event) throws ExecutionException, InterruptedException {
        eventService.createEvent(event);
//        ResponseEntity.ok("{'response': 'Event received'}");
    }

    @GetMapping("{eventId}")
    public event getEventById(@PathVariable String eventId) throws ExecutionException, InterruptedException {
        return eventService.getEvent(eventId);
    }

    @DeleteMapping("/{id}")
    public String deleteEvent(@PathVariable String id) throws ExecutionException, InterruptedException {
        return eventService.deleteEvent(id);
    }

//    // Update Event Status
//    @PostMapping("/{eventId}/update-status")
//    public void updateEventStatus(@PathVariable String eventId) throws ExecutionException, InterruptedException {
//        eventService.updateEventStatus(eventId);
//    }

    // Mark Visitor as Attended
    @PostMapping("/{eventId}&{userId}")
    public void addAttendee(@PathVariable String eventId, String userId) throws ExecutionException, InterruptedException {
        eventService.addAttendee(eventId, userId);
    }
}