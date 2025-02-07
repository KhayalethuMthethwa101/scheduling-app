package com.events_manager.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.*;

import java.time.LocalDateTime;
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

    public String addFeedback(String eventId, String visitorId, int rating, int recommendation, String comment) throws ExecutionException, InterruptedException {
        // Check if the visitor has booked this event
        List<booking> bookings = bookingRepository.getAllBookings();
        boolean hasBooking = bookings.stream()
                .anyMatch(booking -> booking.getEventId().equals(eventId) && booking.getUserId().equals(visitorId));

        if (!hasBooking) {
            throw new IllegalArgumentException("Only visitors who booked the event can leave feedback.");
        }

        // Check if the event has already ended
        event event = eventRepository.getEventById(eventId);
        if (event == null || event.getDateOfEvent().isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("You can only leave feedback after attending the event.");
        }

        // Save feedback
        String feedbackId = UUID.randomUUID().toString();
        feedback feedback = new feedback(feedbackId, eventId, visitorId, rating, recommendation, comment);
        return feedbackRepository.saveFeedback(feedback);
    }

    public feedback getFeedback(String id) throws ExecutionException, InterruptedException {
        return feedbackRepository.getFeedbackById(id);
    }

    public List<feedback> getFeedbackByEvent(String eventId) throws ExecutionException, InterruptedException {
        return feedbackRepository.getFeedbackByEventId(eventId);
    }
}
