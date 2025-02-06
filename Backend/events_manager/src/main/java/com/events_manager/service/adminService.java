package com.events_manager.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.events_manager.repository.*;

@Service
public class adminService {
    @Autowired
    private visitorRepository visitorRepository;
    @Autowired
    private bookingRepository bookingRepository;


    public List<String> getEventAttendees(String eventId) {
        return bookingRepository.findAttendeesByEventId(eventId);
    }
}