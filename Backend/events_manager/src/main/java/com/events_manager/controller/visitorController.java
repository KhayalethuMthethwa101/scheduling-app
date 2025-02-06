package com.events_manager.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.events_manager.model.visitor;
import com.events_manager.service.visitorService;

@RestController
@RequestMapping("/api/visitors")
public class visitorController {
    @Autowired
    private visitorService visitorService;

    @PostMapping("/signup")
    public String signUp(@RequestBody visitor visitorDto) {
        return visitorService.signUp(visitorDto);
    }

    @PostMapping("/rsvp/{eventId}/{visitorId}")
    public String rsvpForEvent(@PathVariable Long eventId, @PathVariable Long visitorId) {
        return "RSVP successful!";
    }

    @PostMapping("/rate/{eventId}")
    public String rateEvent(@PathVariable Long eventId, @RequestParam int rating) {
        return "Event rated successfully!";
    }

    @PostMapping("/comment/{eventId}")
    public String commentOnEvent(@PathVariable Long eventId, @RequestBody String comment) {
        return "Comment added successfully!";
    }
}
