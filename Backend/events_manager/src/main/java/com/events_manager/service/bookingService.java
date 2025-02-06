package com.events_manager.service;
import com.events_manager.model.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.events_manager.repository.*;

@Service
public class bookingService {
    @Autowired
    private bookingRepository bookingRepository;

    public String rsvpForEvent(event event, visitor visitor) {
        bookingRepository.save(new booking(event, visitor));
        return "RSVP successful!";
    }

    public List<String> getEventAttendees(String eventId) {
        return bookingRepository.findAttendeesByEventId(eventId);
    }
}