package com.events_manager;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

@SpringBootApplication(scanBasePackages = "com.events_manager")
public class EventsManagerApplication {

    public static void main(String[] args) throws IOException {
        // Load Firebase config from resources inside the JAR
        InputStream serviceAccount = new ClassPathResource("firebase-service-key.json").getInputStream();

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        if (FirebaseApp.getApps().isEmpty()) { // Ensure Firebase is initialized only once
            FirebaseApp.initializeApp(options);
        }

        SpringApplication.run(EventsManagerApplication.class, args);
    }
}

