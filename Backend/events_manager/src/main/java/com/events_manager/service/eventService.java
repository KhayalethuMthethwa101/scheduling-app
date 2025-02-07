package com.events_manager.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.events_manager.repository.*;
import com.events_manager.model.*;
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
}