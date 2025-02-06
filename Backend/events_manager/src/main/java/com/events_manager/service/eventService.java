package com.events_manager.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.events_manager.repository.*;
import com.events_manager.model.*;

@Service
public class eventService {
    @Autowired
    private eventRepository eventRepository;

    public String createEvent(event eventDto) {
        eventRepository.save(eventDto);
        return "Event created successfully!";
    }

    public String updateEvent(Long eventId, event eventDto) {
        eventRepository.update(eventId, eventDto);
        return "Event updated successfully!";
    }
}