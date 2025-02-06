package com.events_manager.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.events_manager.service.*;

@RestController
@RequestMapping("/api/dashboard")
public class dashboardController {
    @Autowired
    private dashboardService dashboardService;

    @GetMapping("/event/{eventId}")
    public String getEventDashboard(@PathVariable String eventId) {
        return dashboardService.getEventDashboard(eventId);
    }
}
