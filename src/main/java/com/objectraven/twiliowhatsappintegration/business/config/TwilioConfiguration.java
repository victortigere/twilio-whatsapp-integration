package com.objectraven.twiliowhatsappintegration.business.config;

import com.twilio.Twilio;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("twilio")
public class TwilioConfiguration {
    private String accountSid;
    private String authToken;
    private String phoneNumber;

    @Bean
    public void initTwilio() {
        Twilio.init(accountSid, authToken);
    }
}
