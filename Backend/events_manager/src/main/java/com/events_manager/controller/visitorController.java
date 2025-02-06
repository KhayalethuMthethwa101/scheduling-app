package com.events_manager.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.events_manager.model.*;
import com.events_manager.service.*;

@RestController
@RequestMapping("/api/visitors")
public class visitorController {
    @Autowired
    private visitorService visitorService;
    private eventService event;

    @PostMapping("/signup")
    public String signUp(@RequestBody visitor visitorDto) {
        return visitorService.signUp(visitorDto);
    }

    @PostMapping("/rsvp/{eventId}/{visitorId}")
    public String rsvpForEvent(@PathVariable String eventId, @PathVariable String visitorId) {
        return "RSVP successful!";
    }

    @PostMapping("/rate/{eventId}")
    public String rateEvent(@PathVariable String eventId, @RequestParam int rating) {
        return "Event rated successfully!";
    }

    @PostMapping("/comment/{eventId}")
    public String commentOnEvent(@PathVariable String eventId, @RequestBody String comment) {
        return "Comment added successfully!";
    }
}
