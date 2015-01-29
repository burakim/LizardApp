package com.burakim.lizardapp.SMS;

/**
 * Created by burak on 29/01/15.
 */
public interface SmsOperationInterface {


    /**
     * This function invokes when new SMS arrives from Cellular Network.
     * @param messageText
     * @param senderNumber
     */

    public void SMSReceived(String messageText, String senderNumber);
}
