package com.events_manager.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.*;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import com.events_manager.repository.*;

@Service
public class feedbackService {
    @Autowired
    private feedbackRepository feedbackRepository;

    public String rateEvent(Long eventId, int rating) {
        feedbackRepository.save(new FeedbackDto(eventId, rating));
        return "Event rated successfully!";
    }

    public String commentOnEvent(Long eventId, String comment) {
        feedbackRepository.save(new FeedbackDto(eventId, comment));
        return "Comment added successfully!";
    }

    public List<String> getEventComments(Long eventId) {
        return feedbackRepository.findCommentsByEventId(eventId);
    }
}