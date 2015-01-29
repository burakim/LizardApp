package com.burakim.lizardapp.SMS;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.util.Objects;

/**
 * Created by burak on 28/01/15.
 */
public class SMSRecevier extends BroadcastReceiver {

    public static SmsOperationInterface operationInterface;

    @Override
    public void onReceive(Context context, Intent intent) {
    Bundle incomingBundle = intent.getExtras();
        SmsMessage[] msgs = null;
        String messageReceived = "";
        if(incomingBundle != null){
            Object[] pduArray = (Object[]) incomingBundle.get("pdus");

            msgs = new SmsMessage[pduArray.length];
            for (int i = 0 ;i<msgs.length ; i++){
                msgs[i] = SmsMessage.createFromPdu((byte[])pduArray[i]);
                messageReceived += msgs[i].getMessageBody().toString();

            }
            if(operationInterface != null) {
                operationInterface.SMSReceived(msgs[0].getMessageBody().toString(), msgs[0].getOriginatingAddress());
            }
            else{
                //todo SMS gelmedigi zaman durumu eklenmeli.
                Log.d("BRK", "Mesaj gelmedi");
            }

        }

    }

    @Override
    public IBinder peekService(Context myContext, Intent service) {
        return super.peekService(myContext, service);
    }
}
