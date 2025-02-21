package com.rapidsw.alteserver.translate.google;

import com.google.api.gax.retrying.RetrySettings;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import jakarta.ws.rs.NotFoundException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.threeten.bp.Duration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class GoogleConf {
    @Bean
    GoogleCredentials googleCredentials() throws IOException {
        InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream("google-cloud-translate.json");
        if (inputStream == null) throw new NotFoundException("google-cloud-translate not found");
        return GoogleCredentials.fromStream(inputStream);
    }

    @Bean
    Translate translate(GoogleCredentials googleCredentials) {
        TranslateOptions translateOptions = TranslateOptions.newBuilder()
                .setCredentials(googleCredentials)
                .setRetrySettings(RetrySettings.newBuilder()
                        .setMaxAttempts(1)
                        .setTotalTimeout(Duration.ofSeconds(2L))
                        .build())
                .build();
        return translateOptions.getService();
    }
}
