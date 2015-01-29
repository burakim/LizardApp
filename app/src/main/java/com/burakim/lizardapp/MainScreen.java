package com.burakim.lizardapp;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;

import android.support.v4.app.NotificationCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.burakim.lizardapp.SMS.SMSRecevier;
import com.burakim.lizardapp.SMS.SmsOperationInterface;
import com.burakim.lizardapp.SMS.SmsOperations;


public class MainScreen extends Activity implements SmsOperationInterface {
private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        SMSRecevier.operationInterface = this;
    button = (Button)findViewById(R.id.bttn);
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SmsOperations sms = SmsOperations.getInstance(getApplicationContext());
              Bundle response =   sms.SMSSend("+905366008981","Test");
            if(!response.isEmpty()) {
                Log.d("Deneme", response.getString("SENT_STATUS_MESSAGE"));
                Log.d("Deneme", response.getString("SMS_DELIVERED_MESSAGE"));
            }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void SMSReceived(String messageText, String senderNumber) {
        NotificationCompat.Builder mBuilder =new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.abc_btn_check_material)
                .setContentTitle(senderNumber)
                .setContentText(messageText);

        NotificationManager mNotificationManager =
                (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(0, mBuilder.build());
    }
}
