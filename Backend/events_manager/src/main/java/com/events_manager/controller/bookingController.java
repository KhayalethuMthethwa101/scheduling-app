package com.events_manager.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class bookingController {
    @Autowired
    private bookingService bookingService;

    @PostMapping("/rsvp/{eventId}/{visitorId}")
    public String rsvpForEvent(@PathVariable Long eventId, @PathVariable Long visitorId) {
        return bookingService.rsvpForEvent(eventId, visitorId);
    }

    @GetMapping("/event/{eventId}/attendees")
    public List<String> getEventAttendees(@PathVariable Long eventId) {
        return bookingService.getEventAttendees(eventId);
    }
}