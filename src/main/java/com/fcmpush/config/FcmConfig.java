package com.fcmpush.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.List;

@Configuration
public class FcmConfig {
    @Bean
    public FirebaseApp initFcm() {
        FirebaseApp firebaseApp = null;
        try {
            List<FirebaseApp> apps = FirebaseApp.getApps();
            if (apps != null && !apps.isEmpty()) {
                for (FirebaseApp app : apps) {
                    if (app.getName().equals(FirebaseApp.DEFAULT_APP_NAME))
                        firebaseApp = app;
                }
            } else {
                InputStream fcmCredential = new ClassPathResource("firebase.json").getInputStream();
                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(fcmCredential))
                        .setProjectId("web-push-76110")
                        .build();
                firebaseApp = FirebaseApp.initializeApp(options);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return firebaseApp;

    }
}
