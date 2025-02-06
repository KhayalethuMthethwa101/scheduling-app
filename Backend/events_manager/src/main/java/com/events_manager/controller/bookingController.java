package com.events_manager.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.events_manager.service.*;
import com.events_manager.model.*;

@RestController
@RequestMapping("/api/bookings")
public class bookingController {
    @Autowired
    private bookingService bookingService;

    @PostMapping("/rsvp/{eventId}/{visitorId}")
    public String rsvpForEvent(@PathVariable event event, @PathVariable visitor visitor) {
        return bookingService.rsvpForEvent(event, visitor);
    }

    @GetMapping("/event/{eventId}/attendees")
    public List<String> getEventAttendees(@PathVariable String eventId) {
        return bookingService.getEventAttendees(eventId);
    }
}