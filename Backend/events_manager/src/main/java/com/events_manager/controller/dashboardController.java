package com.events_manager.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
public class dashboardController {
    @Autowired
    private dashboardService dashboardService;

    @GetMapping("/festival")
    public String getFestivalDashboard() {
        return dashboardService.getFestivalDashboard();
    }

    @GetMapping("/event/{eventId}")
    public String getEventDashboard(@PathVariable Long eventId) {
        return dashboardService.getEventDashboard(eventId);
    }
}
