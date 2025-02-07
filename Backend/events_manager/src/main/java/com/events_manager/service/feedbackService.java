package com.events_manager.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.*;
import java.util.List;
import com.events_manager.repository.*;
import com.events_manager.model.*;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class feedbackService {
    private final feedbackRepository feedbackRepository;
    private final bookingRepository bookingRepository;
    private final eventRepository eventRepository;

    public feedbackService(feedbackRepository feedbackRepository, bookingRepository bookingRepository, eventRepository eventRepository) {
        this.feedbackRepository = feedbackRepository;
        this.bookingRepository = bookingRepository;
        this.eventRepository = eventRepository;
    }

    public String addFeedback(String eventId, String visitorId, int rating, String comment) throws ExecutionException, InterruptedException {
        // Check if the visitor has booked this event
        List<booking> bookings = bookingRepository.getAllBookings();
        boolean hasBooking = bookings.stream()
                .anyMatch(booking -> booking.getEventId().equals(eventId) && booking.getVisitorId().equals(visitorId));

        if (!hasBooking) {
            throw new IllegalArgumentException("Only visitors who booked the event can leave feedback.");
        }

        // Check if the event has already ended
        Event event = eventRepository.getEventById(eventId);
        if (event == null || event.getDate().after(new java.util.Date())) {
            throw new IllegalArgumentException("You can only leave feedback after attending the event.");
        }

        // Save feedback
        String feedbackId = UUID.randomUUID().toString();
        Feedback feedback = new Feedback(feedbackId, eventId, visitorId, rating, comment, true);
        return feedbackRepository.saveFeedback(feedback);
    }

    public Feedback getFeedback(String id) throws ExecutionException, InterruptedException {
        return feedbackRepository.getFeedbackById(id);
    }

    public List<Feedback> getFeedbackByEvent(String eventId) throws ExecutionException, InterruptedException {
        return feedbackRepository.getFeedbackByEventId(eventId);
    }
}
