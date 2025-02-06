package com.events_manager.service;
import com.events_manager.model.feedback;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.*;
import java.util.List;
import com.events_manager.repository.*;
import com.events_manager.model.*;

@Service
public class feedbackService {
    @Autowired
    private feedbackRepository feedbackRepository;

    public String rateEvent(event event, int rating) {
        feedbackRepository.save(new feedback(event, rating));
        return "Event rated successfully!";
    }

//    public String commentOnEvent(event event, String comment) {
//        feedbackRepository.save(new feedback(event, comment));
//        return "Comment added successfully!";
//    }

    public List<String> getEventComments(String eventId) {
        return feedbackRepository.findCommentsByEventId(eventId);
    }

}