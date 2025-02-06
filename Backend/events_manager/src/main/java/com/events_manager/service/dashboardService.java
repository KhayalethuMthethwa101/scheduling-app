package com.events_manager.service;
import org.springframework.stereotype.Service;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.CollectionReference;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class dashboardService {
    @Autowired
    private Firestore firestore;

    public String getFestivalDashboard() {
        return "Festival dashboard data (retrieved from Firestore)";
    }

    public String getEventDashboard(Long eventId) {
        return "Event dashboard data (retrieved from Firestore)";
    }
}