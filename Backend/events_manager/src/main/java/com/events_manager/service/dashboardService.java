package com.events_manager.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.events_manager.repository.*;

@Service
public class dashboardService {
    @Autowired
    private dashboardRepository dashboardRepository;

    public String getEventDashboard(String eventId) {
        return dashboardRepository.getEventDashboardData(eventId);
    }
}