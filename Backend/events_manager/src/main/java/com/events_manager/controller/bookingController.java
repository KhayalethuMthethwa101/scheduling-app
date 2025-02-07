package com.events_manager.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.events_manager.service.*;
import com.events_manager.model.*;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/bookings")
public class bookingController {

    private final bookingService bookingService;

    @Autowired
    public bookingController(bookingService bookingService) {
        this.bookingService = bookingService;
    }

    // Create a Booking (Only Visitors Allowed)
    @PostMapping
    public String addBooking(@RequestParam String eventId, @RequestParam String visitorId) throws ExecutionException, InterruptedException {
        return bookingService.addBooking(eventId, visitorId);
    }

    // Retrieve a Booking
    @GetMapping("/{id}")
    public booking getBooking(@PathVariable String id) throws ExecutionException, InterruptedException {
        return bookingService.getBooking(id);
    }

    // Delete a Booking
    @DeleteMapping("/{id}")
    public String deleteBooking(@PathVariable String id) throws ExecutionException, InterruptedException {
        return bookingService.deleteBooking(id);
    }

    // Retrieve All Bookings
    @GetMapping
    public List<booking> getAllBookings() throws ExecutionException, InterruptedException {
        return bookingService.getAllBookings();
    }
}
