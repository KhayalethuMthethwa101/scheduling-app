package com.events_manager.controller;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
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
    public eventController(eventService eventService){
        this.eventService = eventService;
    }

    @GetMapping
    public List<event> getAllEvents() throws ExecutionException, InterruptedException {
        return eventService.getAllEvents();
    }

    @PostMapping
    public String createEvent(@RequestBody event eventDto) throws ExecutionException, InterruptedException {
        return eventService.createEvent(eventDto);
    }

    @GetMapping("{eventId}")
    public event getEventById(@PathVariable String eventId) throws ExecutionException, InterruptedException {
        return eventService.getEvent(eventId);
    }

    @DeleteMapping("/{id}")
    public String deleteEvent(@PathVariable String id) throws ExecutionException, InterruptedException {
        return eventService.deleteEvent(id);
    }
}