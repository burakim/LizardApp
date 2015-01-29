package com.burakim.lizardapp.SMS;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;
import android.os.Bundle;

import com.burakim.lizardapp.R;

/**
 * Created by burak on 28/01/15.
 */
public  class SmsOperations {
    private static SmsOperations instance = null;

    public static SmsOperations getInstance(Context context) {

        if(instance == null && context != null)
        {
            instance =  new SmsOperations(context);
        }

        return instance;
    }

    private String SENT = "SMS_SENT";
    String DELIVERED = "SMS_DELIVERED";

    PendingIntent sentPI;

    PendingIntent deliveredPI;
    private Context context;
    public SmsOperations(){

    }

    public SmsOperations(Context context){
        this.setContext(context);
    }

    public Bundle SMSSend(String phoneNumber, String message){

        sentPI = PendingIntent.getBroadcast(getContext(),0,new Intent(SENT),0);
        deliveredPI = PendingIntent.getBroadcast(getContext(), 0 ,new Intent(DELIVERED),0);
        final Bundle responseData = new Bundle();


        getContext().registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Boolean result = false;
                String message;
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        result = true;
                        message = "SMS SENT";
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        result = false;
                        message = "Generic Failure";
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        result = false;
                        message = "PDU NULL";
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        result = false;
                        message = "GSM Radio Off";
                        break;
                    default:
                        result = false;
                        message = "Unrecognized result code occured";
                        break;
                }
                responseData.putBoolean("SENT_STATUS", result);
                responseData.putString("SENT_STATUS_MESSAGE", message);
            }
        }, new IntentFilter(SENT));

        getContext().registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Boolean result = false;
                String message;
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        result = true;
                        message = "MESSAGE DELIVERED";
                    case Activity.RESULT_CANCELED:
                        result = false;
                        message = "Message not DELIVERED";
                    default:
                        result = false;
                        message = "Unrecognized result code occured";
                }
                responseData.putBoolean("SMS_DELIVERED_STATUS", result);
                responseData.putString("SMS_DELIVERED_MESSAGE", message);
            }
        }, new IntentFilter(DELIVERED));


        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber,null,message,sentPI,deliveredPI);

        return responseData;
    }




    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
