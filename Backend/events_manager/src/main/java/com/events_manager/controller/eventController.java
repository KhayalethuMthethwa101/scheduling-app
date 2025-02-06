package com.events_manager.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.events_manager.service.eventService;
import com.events_manager.model.event;

@RestController
@RequestMapping("/api/events")
public class eventController {
    @Autowired
    private eventService eventService;

    @PostMapping("/create")
    public String createEvent(@RequestBody event eventDto) {
        return eventService.createEvent(eventDto);
    }

    @PutMapping("/update/{eventId}")
    public String updateEvent(@PathVariable Long eventId, @RequestBody event eventDto) {
        return eventService.updateEvent(eventId, eventDto);
    }
}