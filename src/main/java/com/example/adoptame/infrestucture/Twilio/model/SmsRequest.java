package com.example.adoptame.infrestucture.Twilio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class SmsRequest {
    private String smsCode;
    private String sidMessage;
    private boolean enabled;
    public boolean verifyCode(String codeClient){
        return this.smsCode.equals(codeClient);
    }
}
