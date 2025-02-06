package com.events_manager.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feedback")
public class feedbackController {
    @Autowired
    private feedbackService feedbackService;

    @PostMapping("/rate/{eventId}")
    public String rateEvent(@PathVariable Long eventId, @RequestParam int rating) {
        return feedbackService.rateEvent(eventId, rating);
    }

    @PostMapping("/comment/{eventId}")
    public String commentOnEvent(@PathVariable Long eventId, @RequestBody String comment) {
        return feedbackService.commentOnEvent(eventId, comment);
    }

    @GetMapping("/event/{eventId}/comments")
    public List<String> getEventComments(@PathVariable Long eventId) {
        return feedbackService.getEventComments(eventId);
    }
}
