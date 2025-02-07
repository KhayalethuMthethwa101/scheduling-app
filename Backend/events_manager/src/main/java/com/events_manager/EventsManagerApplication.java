package com.events_manager;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static java.util.Objects.*;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.events_manager.repository") // Add this if needed
@EntityScan(basePackages = "com.events_manager.model") // Ensure entity scanning
public class EventsManagerApplication {

	@Configuration
	public static class FirebaseConfig {

		@Bean
		public Firestore initializeFirebase() {
			if (FirebaseApp.getApps().isEmpty()) { // Check if FirebaseApp already exists
				try {
					ClassLoader classloader = EventsManagerApplication.class.getClassLoader();
					File file = new File(requireNonNull(classloader.getResource("firebase-service-key.json")).getFile());
					FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());



					FirebaseOptions options = FirebaseOptions.builder()
							.setCredentials(GoogleCredentials.fromStream(serviceAccount))
							.build();

					FirebaseApp.initializeApp(options);
					return FirestoreClient.getFirestore();
				} catch (IOException e) {
					throw new RuntimeException("Failed to initialize Firebase", e);
				}
			}
            return null;
        }
	}

	public static void main(String[] args) throws IOException {

		FirebaseConfig config = new FirebaseConfig();
		config.initializeFirebase();
		SpringApplication.run(EventsManagerApplication.class, args);
	}

}

