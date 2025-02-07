package com.events_manager.service;
import com.google.api.client.util.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.events_manager.repository.*;
import com.events_manager.model.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class eventService {
    private final eventRepository eventRepository;

    @Autowired
    public eventService(eventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    public String createEvent(event eventDto) throws ExecutionException, InterruptedException {
        return eventRepository.saveEvent(eventDto);
    }

    public event getEvent(String eventId) throws ExecutionException, InterruptedException {
        return eventRepository.getEventById(eventId);
    }

    public String deleteEvent(String id) throws ExecutionException, InterruptedException {
        return eventRepository.deleteEvent(id);
    }

    // Fetch All Events
    public List<event> getAllEvents() throws ExecutionException, InterruptedException {
        return eventRepository.getAllEvents();
    }

    // ✅ Check and update event status
    public void updateEventStatus(String eventId) throws ExecutionException, InterruptedException {
        event event = eventRepository.getEventById(eventId);
        if (event == null) {
            throw new IllegalArgumentException("Event not found.");
        }

        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(event.getDateOfEvent())) {
            eventRepository.updateEventStatus(eventId, "COMING");
        } else if (now.isAfter(event.getDateOfEvent()) && now.isBefore(event.getDateOfEvent().plusHours(24))) {
            eventRepository.updateEventStatus(eventId, "ACTIVE");
        } else {
            eventRepository.updateEventStatus(eventId, "ENDED");
        }
    }

    // ✅ Mark user as attended
    public void markUserAsAttended(String eventId, String visitorId) throws ExecutionException, InterruptedException {
        event event = eventRepository.getEventById(eventId);
        if (event == null || !"ENDED".equals(event.getStatus())) {
            throw new IllegalArgumentException("User can only be marked attended after the event ends.");
        }

        eventRepository.addAttendee(eventId, visitorId);
    }
}