package com.events_manager;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Objects;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@SpringBootApplication(scanBasePackages = "com.events_manager")
public class EventsManagerApplication{

	public static void main(String[] args) throws IOException {
		ClassLoader classLoader = EventsManagerApplication.class.getClassLoader();

		File file = new File(Objects.requireNonNull(classLoader.getResource("firebase-service-key.json")).getFile());
		FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.build();

		if (FirebaseApp.getApps().isEmpty()) { // Ensure Firebase is initialized only once
			FirebaseApp.initializeApp(options);
		}

		SpringApplication.run(EventsManagerApplication.class, args);
	}
}

