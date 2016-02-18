package com.example.minorproject;


import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class Reminder extends Activity implements OnClickListener
{
	ImageButton add,setting,search,fb,cal,upc;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reminder);
		add=(ImageButton)findViewById(R.id.imageButton3);
		setting=(ImageButton)findViewById(R.id.imageButton2);
		search=(ImageButton)findViewById(R.id.imageButton1);
		cal=(ImageButton)findViewById(R.id.imageButton4);
		fb=(ImageButton)findViewById(R.id.imageButton6);
		upc=(ImageButton)findViewById(R.id.imageButton5);
		add.setOnClickListener(this);
		setting.setOnClickListener(this);
		search.setOnClickListener(this);
		cal.setOnClickListener(this);
		fb.setOnClickListener(this);
		upc.setOnClickListener(this);
		  Intent serv= new Intent(this,BirthService.class);
			
			startService(serv);

		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_reminder, menu);
		return true;
	}

	
	/*@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) {
		case R.id.abt:
			
			Intent i= new Intent(this,Aboutapp.class);
			startActivity(i);
			return true;
		case R.id.conta:
			
			Intent i1= new Intent(this,Contact.class);
			startActivity(i1);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
		
		
	}*/
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.imageButton3)
		{
			Intent i= new Intent(this,AddBirthday.class);
			startActivity(i);

			
		}
		if(v.getId()==R.id.imageButton2)
		{
			Intent i= new Intent(this,Settings.class);
			startActivity(i);
		}
		if(v.getId()==R.id.imageButton4)
		{
			Intent i= new Intent(this,BashCalendar.class);
			startActivity(i);
		}
		if(v.getId()==R.id.imageButton1)
		{
			Intent i= new Intent(this,Search.class);
			startActivity(i);
		}
		if(v.getId()==R.id.imageButton5)
		{
			Intent i= new Intent(this,Upcoming.class);
			startActivity(i);
		}
		if(v.getId()==R.id.imageButton6)
		{
			Intent i= new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.facebook.com"));
			startActivity(i);
		}
		
	}

}
