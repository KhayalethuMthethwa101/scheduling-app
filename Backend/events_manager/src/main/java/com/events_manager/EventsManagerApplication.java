package com.events_manager;

import com.events_manager.config.FirebaseConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;

@SpringBootApplication(scanBasePackages = "com.events_manager.config")
public class EventsManagerApplication{

	public static void main(String[] args) throws IOException {
		SpringApplication.run(EventsManagerApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		// Initialize Firebase at the application startup
//		FirebaseConfig.initializeFirebase();
//	}
}

