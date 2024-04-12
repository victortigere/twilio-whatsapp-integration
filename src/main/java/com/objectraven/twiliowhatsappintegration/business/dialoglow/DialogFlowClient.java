package com.objectraven.twiliowhatsappintegration.business.dialoglow;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.dialogflow.v2.*;
import com.objectraven.twiliowhatsappintegration.business.utilities.SessionUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;

@Service
public class DialogFlowClient implements DialogflowClientService{
    static String sessionId = SessionUtil.generateSessionId();
    String projectId = "domassi-lnxo";
    private static final String DIALOGFLOW_API_KEY = "38108e7e4572decc249a89f95a4332a42303500a";
    String filePath = "C:\\Certs\\domassi-lnxo-38108e7e4572.json";

    @Override
    public  ResponseEntity<String> sendMessageToDialogflow(String message) throws IOException {
        System.out.println(message);

        try {
            SessionsSettings sessionsSettings = SessionsSettings.newBuilder()
                    .setCredentialsProvider(FixedCredentialsProvider.create(GoogleCredentials.fromStream(new FileInputStream(filePath))))
                    .build();
            SessionsClient sessionsClient = SessionsClient.create(sessionsSettings);

            SessionName session = SessionName.of(projectId, sessionId);
            TextInput.Builder textInput = TextInput.newBuilder().setText(message).setLanguageCode("en-US");
            QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();

            DetectIntentResponse response = sessionsClient.detectIntent(session, queryInput);
            QueryResult queryResult = response.getQueryResult();
            String fulfillmentText = queryResult.getFulfillmentText();

            System.out.println(fulfillmentText);

            sessionsClient.close();

            return new ResponseEntity<>(fulfillmentText, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
