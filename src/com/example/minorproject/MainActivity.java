package com.example.minorproject;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.app.Activity;

import android.content.Intent;
import android.view.Menu;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timer t=new Timer();
      //  boolean checkConnection=new ApplicationUtility().checkConnection(this);
       // if(checkConnection)
        //{
        	t.schedule(new splash(),3000);
        	//Toast.makeText(this,"Sign class",Toast.LENGTH_LONG).show();
        //}
        /*else
        {
        	Toast.makeText(this,"No Connection",3000).show();
        }*/
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    class splash extends TimerTask
    {

		@Override
		public void run()
		{
			Intent i=new Intent(MainActivity.this,HomePageMain.class);
			finish();
			startActivity(i);
		}
    	
    }
    
}

