package com.objectraven.twiliowhatsappintegration.business.dialoglow;

import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface DialogflowClientService {

    ResponseEntity<String> sendMessageToDialogflow(String message) throws IOException;
}
