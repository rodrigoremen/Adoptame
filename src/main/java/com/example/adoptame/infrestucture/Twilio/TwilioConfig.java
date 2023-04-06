package com.example.adoptame.infrestucture.Twilio;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
@Data
@NoArgsConstructor
@Configuration
public class TwilioConfig {
    @Value("${twilio.account_sid}")
    private String accountSid;
    @Value("${twilio.auth_token}")
    private String authToken;
    @Value("${twilio.phone_number}")
    private String phoneNumber;
}
