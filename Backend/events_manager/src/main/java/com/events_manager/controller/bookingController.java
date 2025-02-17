package com.events_manager.controller;
import java.util.Date;
import java.util.List;

import com.google.cloud.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.events_manager.service.*;
import com.events_manager.model.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/v1/bookings")
public class bookingController {
    @Autowired
    private bookingService bookingService;

    // Retrieve All Bookings
    @GetMapping
    public List<booking> getAllBookings() throws ExecutionException, InterruptedException {
        return bookingService.getAllBookings();
    }
    // Create a Booking (Only Visitors Allowed)
    @PostMapping
    public String addBooking(@RequestBody booking booking) throws ExecutionException, InterruptedException {
        return bookingService.addBooking(booking);
    }

    // Retrieve a Booking
    @GetMapping("/{id}")
    public booking getBooking(@PathVariable String id) throws ExecutionException, InterruptedException {
        return bookingService.getBooking(id);
    }

    // Delete a Booking
    @DeleteMapping("/delete/{bookingId}")
    public void deleteBooking(@PathVariable String bookingId) throws ExecutionException, InterruptedException {
        bookingService.deleteBooking(bookingId);
    }
    @GetMapping("/user/{email}")
    public List<booking> getBookingsByEmail(@PathVariable String email) throws ExecutionException, InterruptedException {
        return bookingService.getBookingsByEmail(email);
    }

    @GetMapping("/admin/bookings")
    public List<Map<String, Object>> getEventBookings() throws ExecutionException, InterruptedException {
        return bookingService.getEventBookings();
    }

    @GetMapping("/admin/gender-distribution")
    public List<Map<String, Object>> getGenderDistribution() throws ExecutionException, InterruptedException {
        return bookingService.getGenderDistribution();
    }

    @GetMapping("/admin/age-distribution")
    public List<Map<String, Object>> getAgeDistribution() throws ExecutionException, InterruptedException {
        return bookingService.getAgeDistribution();
    }

}
