package org.odnokursniki.auth.service;


/**
 * An interface that defines operations for sending an SMS code
 *
 * @since 0.1.0
 * @author pulsar
 */
public interface SmsService {

    /**
     * Sends an SMS code to the given {@code phoneNumber}
     * @param phoneNumber client phone number
     * @return the sent SMS code
     */
    String send(String phoneNumber);
}
