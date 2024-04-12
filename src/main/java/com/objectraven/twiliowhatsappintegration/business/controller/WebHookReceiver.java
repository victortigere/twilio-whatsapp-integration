package com.objectraven.twiliowhatsappintegration.business.controller;

import com.objectraven.twiliowhatsappintegration.business.config.TwilioConfiguration;
import com.objectraven.twiliowhatsappintegration.business.dialoglow.DialogFlowClient;
import com.objectraven.twiliowhatsappintegration.business.payload.WebHookRequest;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/twilio/")
public class WebHookReceiver{
    private final TwilioConfiguration twilioConfiguration;

    DialogFlowClient dialogFlowClient = new DialogFlowClient();

    @PostMapping(value = "whatsapp")
    public void handleWebhook(WebHookRequest webhookRequest) throws IOException {
        String requestBody = webhookRequest.getFrom();
        String message = webhookRequest.getBody();
        String[] phoneNumber = requestBody.split(":");
        ResponseEntity<String> response = dialogFlowClient.sendMessageToDialogflow(message);
        // First Number is "To", second number is From
        System.out.println(requestBody);
        System.out.println(phoneNumber[1]);
        Message.creator(new PhoneNumber("whatsapp:+263779477473"),
                new PhoneNumber("whatsapp:+14155238886"), response.getBody()).create();
    }
}



