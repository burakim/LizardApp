package com.burakim.lizardapp.MMS;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by burak on 28/01/15.
 */
public class MMSReceiver  extends BroadcastReceiver{
    public MMSReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {

    }

    @Override
    public IBinder peekService(Context myContext, Intent service) {
        return super.peekService(myContext, service);
    }
}
