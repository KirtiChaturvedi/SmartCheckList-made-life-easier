package com.example.minorproject;


import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class GirlActivity extends TabActivity {

	TabHost tabHost;
	TabSpec spec1,spec2,spec3;

	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_page);
		this.getActionBar().setTitle("Smart CheckList");
		tabHost=(TabHost)findViewById(android.R.id.tabhost);
		tabHost.setup();
		spec1=tabHost.newTabSpec("Tab 1");
		spec1.setContent(R.id.tab1);
		spec1.setIndicator("Shopping",getResources().getDrawable(R.drawable.ic_launcher));
		Intent i1=new Intent(this,ShoppingGirl.class);
		spec1.setContent(i1);
		tabHost.addTab(spec1);
		
		spec2=tabHost.newTabSpec("Tab 2");
		spec2.setContent(R.id.tab2);
		spec2.setIndicator("Routine",getResources().getDrawable(R.drawable.ic_launcher));
		Intent i2=new Intent(this,DailyRoutineGirl.class);
		spec2.setContent(i2);
		tabHost.addTab(spec2);

		
		spec3=tabHost.newTabSpec("Tab 3");
		spec3.setContent(R.id.tab3);
		spec3.setIndicator("Reminder",getResources().getDrawable(R.drawable.ic_launcher));
		Intent i3=new Intent(this,Reminder.class);
		spec3.setContent(i3);
		tabHost.addTab(spec3);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home_page, menu);
		return true;
	}

}



