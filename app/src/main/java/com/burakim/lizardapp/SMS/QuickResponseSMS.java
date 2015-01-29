package com.burakim.lizardapp.SMS;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by burak on 28/01/15.
 */
public class QuickResponseSMS extends IntentService{

    public QuickResponseSMS(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {


    }
}
