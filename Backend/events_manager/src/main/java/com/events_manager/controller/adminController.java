package com.events_manager.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.events_manager.service.adminService;

@RestController
@RequestMapping("/api/admin")
public class adminController {
    @Autowired
    private adminService adminService;

    @GetMapping("/festival/attendees")
    public List<String> getFestivalAttendees() {
        return adminService.getFestivalAttendees();
    }

    @GetMapping("/event/{eventId}/attendees")
    public List<String> getEventAttendees(@PathVariable Long eventId) {
        return adminService.getEventAttendees(eventId);
    }
}
