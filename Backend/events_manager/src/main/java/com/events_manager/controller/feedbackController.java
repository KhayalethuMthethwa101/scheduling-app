package com.events_manager.controller;
import java.util.List;
import com.events_manager.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.events_manager.model.*;

@RestController
@RequestMapping("/api/feedback")
public class feedbackController {
    @Autowired
    private feedbackService feedbackService;

    @PostMapping("/rate/{eventId}")
    public String rateEvent(@PathVariable event event, @RequestParam int rating) {
        return feedbackService.rateEvent(event, rating);
    }

//    @PostMapping("/comment/{eventId}")
//    public String commentOnEvent(@PathVariable String eventId, @RequestBody String comment) {
//        return feedbackService.commentOnEvent(eventId, comment);
//    }

    @GetMapping("/event/{eventId}/comments")
    public List<String> getEventComments(@PathVariable String eventId) {
        return feedbackService.getEventComments(eventId);
    }
}
