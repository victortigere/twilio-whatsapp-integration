package com.objectraven.twiliowhatsappintegration.business.utilities;

import java.util.UUID;

public class SessionUtil {
    public static String generateSessionId() {
        // Generate a unique session ID using UUID
        return UUID.randomUUID().toString();
    }
}
