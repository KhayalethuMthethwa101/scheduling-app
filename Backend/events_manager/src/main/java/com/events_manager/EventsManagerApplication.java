package com.events_manager;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

@SpringBootApplication
public class EventsManagerApplication {

	public static void main(String[] args) {
		ClassLoader classloader = EventsManagerApplication.class.getClassLoader();
		
		//Boilerplate code
		File file = new File(Objects.requireNonNull(classloader.getResource("filebase-service-key.json")).getFile());
		FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());
		FirebaseOptions options = new FirebaseOptions.Builder()
			.setCredentials(GoogleCredentials.fromStream(serviceAccount))
			.build();

		FirebaseApp.initializeApp(options);


		SpringApplication.run(EventsManagerApplication.class, args);
	}

}

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import com.google.auth.oauth2.GoogleCredentials;

// @Configuration
// public class FirebaseConfig {
    
//     @Bean
//     public Firestore firestore() throws IOException {
//         FileInputStream serviceAccount = new FileInputStream("path/to/serviceAccountKey.json");

//         FirebaseOptions options = FirebaseOptions.builder()
//                 .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                 .build();

//         if (FirebaseApp.getApps().isEmpty()) {
//             FirebaseApp.initializeApp(options);
//         }

//         return FirestoreClient.getFirestore();
//     }
// }
