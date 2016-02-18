package com.example.minorproject;


import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

public class MyWakefulReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

   
        Intent service = new Intent(context, BirthService.class);
        startWakefulService(context, service);
    }


    

	
}
