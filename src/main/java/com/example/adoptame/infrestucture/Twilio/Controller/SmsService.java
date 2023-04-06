package com.example.adoptame.infrestucture.Twilio.Controller;

import com.example.adoptame.infrestucture.Twilio.TwilioConfig;
import com.example.adoptame.infrestucture.Twilio.model.SmsRequest;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsService {
    @Autowired
    TwilioConfig twilioConfig;
    public SmsService(TwilioConfig twilioConfig){
        this.twilioConfig = twilioConfig;
        Twilio.init(twilioConfig.getAccountSid(),twilioConfig.getAuthToken());
    }

    // metodo para enviar codigo de verificacion
    public SmsRequest sendCodeSms(String phoneNumber, String codeSms){
        Message message = Message.creator(
                new PhoneNumber(phoneNumber),
                new PhoneNumber(twilioConfig.getPhoneNumber()),codeSms
        ).create();
        return SmsRequest.builder()
                .smsCode(codeSms)
                .sidMessage(message.getSid())
                .enabled(true)
                .build();
    }

}
