package com.events_manager.controller;
import java.util.Date;
import java.util.List;

import com.google.cloud.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.events_manager.service.*;
import com.events_manager.model.*;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/bookings")
public class bookingController {
    @Autowired
    private bookingService bookingService;

    // Create a Booking (Only Visitors Allowed)
    @PostMapping
    public String addBooking(@RequestParam String eventId, String userId, Timestamp bookingDate) throws ExecutionException, InterruptedException {
        String bookingId = UUID.randomUUID().toString();
        booking booking = new booking(bookingId, userId, eventId, bookingDate);
        return bookingService.addBooking(booking);
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
