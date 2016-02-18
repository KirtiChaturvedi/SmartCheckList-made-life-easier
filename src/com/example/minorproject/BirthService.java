package com.example.minorproject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;



import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

public class BirthService extends Service 
{	
	public MediaPlayer media;
    private static Timer timer = new Timer(); 
    private Context ctx;
    private WakeLock wakeLock;
    public static int mcount;
    int startFrom=0000;
    int endAt=26000;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
    	
      return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
    	 
			return null;
    }

    @Override
    public void onCreate() {
        //Toast.makeText(this, "The new Service was Created", Toast.LENGTH_SHORT).show();
        super.onCreate();
        ctx = this; 
        startService();
        

      
        PowerManager mgr = (PowerManager)this.getSystemService(Context.POWER_SERVICE);
        wakeLock = mgr.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Wake locker");
        wakeLock.acquire();
        

    }

    private void startService()
    {           
        timer.scheduleAtFixedRate(new mainTask(), 0, 26000);
       
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Toast.makeText(this, " Service Started", Toast.LENGTH_LONG).show();
        Bundle extras = intent.getExtras();
	      
        MyWakefulReceiver.completeWakefulIntent(intent);
        startService();
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }

    private class mainTask extends TimerTask
    { 
        public void run() 
        {
            toastHandler.sendEmptyMessage(0);
        }
    }  

    private final Handler toastHandler = new Handler()
    {
        
    	@Override
        public void handleMessage(Message msg)
        {
        if(Settings.notifChecked1==1)
          {
        	if(DBhelper.count==1)
        	
        	{
            	Calendar cal = Calendar.getInstance();
            	
            Date currentLocalTime = cal.getTime();
            SimpleDateFormat date = new SimpleDateFormat("h:m");
             
                String currenttime = date.format(currentLocalTime); 

                SimpleDateFormat date2 = new SimpleDateFormat("d/M/yyyy");
                
                    String currentday = date2.format(currentLocalTime); 
                   //  String dy="3/8/2015";
                     DBhelper db= new DBhelper(getApplicationContext());
                    String list2= db.checkDate(currentday);
                   String list3= db.checkTime(currenttime);
                     
                   
            if(currentday.equals(list2)) { // 
               //String tijd1 = "10:42:10";

                if(currenttime.equals(list3))
                {
                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(BirthService.this)
                    		
                            .setSmallIcon(R.drawable.ic_launcher)
                            .setContentTitle("Birthday-Bash Reminder")
                         
                            
                    .setContentText(" Today is birthday");
                    Intent resultIntent = new Intent(BirthService.this, Upcoming.class);
                  
                    PendingIntent resultPendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, resultIntent, 0);
                    
                    mBuilder.setContentIntent(resultPendingIntent);
                    
                    NotificationManager mNotificationManager = (NotificationManager) BirthService.this.getSystemService(Context.NOTIFICATION_SERVICE);
                    mNotificationManager.notify(1, mBuilder.build());
                    Notification notif=mBuilder.build();
                	notif.flags=notif.flags|Notification.FLAG_AUTO_CANCEL;
                	mcount=1;
                	 if(Settings.notifChecked2==1)
                 	{
                     	if(mcount==1)
                     	{	media=MediaPlayer.create(getApplicationContext(), R.raw.bdaysong);
             	    		Runnable stopPlayerTask = new Runnable(){
             			        @Override
             			        public void run() {
             			            media.pause();
             			        }};
             	    		media.seekTo(startFrom);
             	    		media.start();
             	
             	    		    Handler handler = new Handler();
             	    		    handler.postDelayed(stopPlayerTask, endAt);
                     	}	    
                 		mcount=0;
                 	} 
                	
                	 if(Settings.notifChecked3==1)
                	 {
                		 Vibrator vbrt=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                		 vbrt.vibrate(6000);
                	 }
                	
                }	
                }
            }              
            }
        }
    }; 

    
}
