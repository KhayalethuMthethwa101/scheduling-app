package com.events_manager.service;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.events_manager.model.*;
import com.events_manager.repository.*;

@Component
public class visitorService {
    private final visitorRepository visitorRepository;

    @Autowired
    public visitorService(visitorRepository visitorRepository){
        this.visitorRepository=visitorRepository;
    }
    public String signUp(visitor visitorDto) {
        visitorRepository.saveVisitor(visitorDto);
        return "Visitor signed up successfully!";
    }
}