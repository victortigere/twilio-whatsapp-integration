package com.objectraven.twiliowhatsappintegration.business.payload;

import lombok.Data;

@Data
public class WebHookRequest {
    private String from;
    private String body;
}
