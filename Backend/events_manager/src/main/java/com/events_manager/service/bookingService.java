package com.events_manager.service;
import com.events_manager.model.*;
import org.springframework.stereotype.Service;
import java.util.List;
import com.events_manager.repository.*;
import java.util.UUID;
import java.util.Date;
import java.util.concurrent.ExecutionException;

@Service
public class bookingService {
    private final bookingRepository bookingRepository;
    private final userRepository userRepository; // To check user role

    public bookingService(bookingRepository bookingRepository, userRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
    }

    public String addBooking(String eventId, String visitorId) throws ExecutionException, InterruptedException {
        // Check if the user exists and is a visitor
        user user = userRepository.getUserById(visitorId);
        if (user == null || !"VISITOR".equals(user.getUserRole())) {
            throw new IllegalArgumentException("Only visitors can book an event.");
        }
        // Create a booking entry
        String bookingId = UUID.randomUUID().toString();
        booking booking = new booking(bookingId, visitorId, eventId, new Date());

        return bookingRepository.saveBooking(booking);
    }

    public booking getBooking(String id) throws ExecutionException, InterruptedException {
        return bookingRepository.getBookingById(id);
    }

    public String deleteBooking(String id) throws ExecutionException, InterruptedException {
        return bookingRepository.deleteBooking(id);
    }

    public List<booking> getAllBookings() throws ExecutionException, InterruptedException {
        return bookingRepository.getAllBookings();
    }
}
