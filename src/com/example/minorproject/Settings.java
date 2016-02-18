package com.example.minorproject;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends Activity implements OnClickListener
{
	TextView del,clr,delb,upc;
	CheckBox ch1,ch2,ch3;
	public static int notifChecked1=1;
	public static int notifChecked2=1;
	public static int notifChecked3=1;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		del=(TextView)findViewById(R.id.textView3);
		delb=(TextView)findViewById(R.id.textView4);
		clr=(TextView)findViewById(R.id.textView2);
		upc=(TextView)findViewById(R.id.textView5);
		ch1=(CheckBox)findViewById(R.id.checkBox1);
		ch2=(CheckBox)findViewById(R.id.checkBox2);
		ch3=(CheckBox)findViewById(R.id.checkBox3);
		clr.setOnClickListener(this);
		del.setOnClickListener(this);
		delb.setOnClickListener(this);
		upc.setOnClickListener(this);
		ch1.setOnClickListener(this);
		ch2.setOnClickListener(this);
		ch3.setOnClickListener(this);
		
		
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_settings, menu);
		return true;
	}

	@Override
	public void onClick(View v) 
	{
	   if(v.getId()==R.id.textView2)
	   {
		   AlertDialog.Builder adb= new AlertDialog.Builder(Settings.this);
			adb.setTitle("Delete All reminders ?");
			adb.setMessage("All reminders will be deleted.");
			adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
					DBhelper db= new DBhelper(getApplicationContext());
					db.deleteAll();
					Toast.makeText(getApplicationContext(), "All contacts Deleted", Toast.LENGTH_LONG).show();
					Intent del= new Intent(getApplicationContext(),Upcoming.class);
					startActivity(del);
				}
			});
			adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
					
				}
			});
			AlertDialog ad=adb.create();
			ad.show();
	   }
		if(v.getId()==R.id.textView3)
		{
			finish();
			Intent i=new Intent(this,Search.class);
			Toast.makeText(getApplicationContext(), "select any reminder", Toast.LENGTH_SHORT).show();
			startActivity(i);
		}
		if(v.getId()==R.id.textView4)
		{
			finish();
			Intent i1=new Intent(this,Search.class);
			Toast.makeText(getApplicationContext(), "select any reminder", Toast.LENGTH_SHORT).show();
			startActivity(i1);
		}
		if(v.getId()==R.id.textView5)
		{
			finish();
			Intent i2=new Intent(this,Upcoming.class);
			startActivity(i2);
		}
		if(v.getId()==R.id.checkBox1)
		{
			if(ch1.isChecked())
			{
				notifChecked1=1;
				ch2.setEnabled(true);
			}
			else
			{
				notifChecked1=0;
				ch2.setEnabled(false);
			}
		}
		if(v.getId()==R.id.checkBox2)
		{
			if(ch1.isChecked())
			{
				notifChecked2=1;
			}
			else
			{
				notifChecked2=0;
			}
		}
		if(v.getId()==R.id.checkBox3)
		{
			if(ch1.isChecked())
			{
				notifChecked3=1;
			}
			else
			{
				notifChecked3=0;
			}
		}
	}

	
		
	}


