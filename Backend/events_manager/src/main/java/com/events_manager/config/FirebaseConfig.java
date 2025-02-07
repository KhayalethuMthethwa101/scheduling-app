package com.events_manager.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

//    @PostConstruct
//    // Initialize Firebase with credentials
//    public static void initializeFirebase() {
//        try {
//            // Load the service account key from the resources directory
//            InputStream serviceAccount = FirebaseConfig.class
//                    .getClassLoader()
//                    .getResourceAsStream("firebase/firebase-service-key.json");
//
//            if (serviceAccount == null) {
//                throw new IllegalStateException("Firebase service account file not found!");
//            }
//
//            // Initialize Firebase if it has not been initialized already
//            if (FirebaseApp.getApps().isEmpty()) {
//                FirebaseOptions options = FirebaseOptions.builder()
//                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                        .build();
//                FirebaseApp.initializeApp(options);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to initialize Firebase", e);
//        }
//    }
    // Bean for Firestore
    @Bean
    public Firestore firestore() throws IOException {
        if (FirebaseApp.getApps().isEmpty()) {
            GoogleCredentials credentials = GoogleCredentials.fromStream(
                    new ClassPathResource("firebase/firebase-service-key.json").getInputStream()
            );

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(credentials)
                    .build();

            FirebaseApp.initializeApp(options);
        }

        return FirestoreClient.getFirestore();
    }
}
