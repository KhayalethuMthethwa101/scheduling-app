package com.events_manager.controller;
import java.util.List;
import com.events_manager.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.events_manager.model.*;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/feedbacks")
public class feedbackController {
    private final feedbackService feedbackService;

    @Autowired
    public feedbackController(feedbackService feedbackService) {
        this.feedbackService=feedbackService;
    }

    // Submit Feedback (Only Visitors Who Attended the Event)
    @PostMapping
    public String addFeedback(
            @RequestParam String eventId,
            @RequestParam String visitorId,
            @RequestParam int rating,
            @RequestParam int recommendation,
            @RequestParam String comment) throws ExecutionException, InterruptedException {
        return feedbackService.addFeedback(eventId, visitorId, rating, recommendation, comment);
    }

    // Retrieve Feedback by ID
    @GetMapping("/{id}")
    public feedback getFeedback(@PathVariable String id) throws ExecutionException, InterruptedException {
        return feedbackService.getFeedback(id);
    }

    // Retrieve All Feedback for an Event
    @GetMapping("/event/{eventId}")
    public List<feedback> getFeedbackByEvent(@PathVariable String eventId) throws ExecutionException, InterruptedException {
        return feedbackService.getFeedbackByEvent(eventId);
    }
}
