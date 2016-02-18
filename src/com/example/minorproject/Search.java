package com.example.minorproject;


import java.util.ArrayList;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

public class Search extends Activity implements OnItemClickListener
{
	AutoCompleteTextView auto;
	ListView l1;
	 String selectvalue1 ;
		CustomList cust;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		auto=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextViewsearch);
		l1=(ListView)findViewById(R.id.listViewsearch);
		DBhelper db= new DBhelper(this);
		
		ArrayList<String> list= db.upcoming();
		ArrayList<String> list1= db.upcomingImage();
		ArrayList<String> list2= db.upcomingDate();
		ArrayList<String> list3= db.upcomingAlrm();
		ArrayList<String> list4= db.upcominglst();
		
	 cust = new CustomList(this, list, list1,list2,list3,list4);
	       
	       
	          l1.setAdapter(cust);  
	          auto.setAdapter(cust);
	  		auto.setThreshold(1);
	                
	                l1.setOnItemClickListener(this);
	                registerForContextMenu(l1);      
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_search, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.editss:
			Toast.makeText(getApplicationContext(), "select any reminder",Toast.LENGTH_SHORT).show();
			return true;
		case R.id.upcom:
			finish(); 
			Intent s1=new Intent(getApplicationContext(),Upcoming.class);
			startActivity(s1);
			return true;
		case R.id.deletess:
			AlertDialog.Builder adb= new AlertDialog.Builder(Search.this);
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
			
			
			return true;
		case R.id.deless:
			Toast.makeText(getApplicationContext(), "select any reminder",Toast.LENGTH_SHORT).show();
			return true;
		case R.id.addnewss:
			finish();
			Intent AD1=new Intent(getApplicationContext(),AddBirthday.class);
			startActivity(AD1);
			
			return true;
		default:
			return super.onOptionsItemSelected(item);
			
		}
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		 selectvalue1 = cust.getItem(position);
		Toast.makeText(this,selectvalue1,Toast.LENGTH_SHORT).show();
		finish();
		Intent go1 =new Intent(this,Details.class);
		DBhelper db= new DBhelper(this);
		db.detailbdy(selectvalue1);
		go1.putExtra("det1",DBhelper.nme);
		go1.putExtra("detls",DBhelper.lastn );
		go1.putExtra("det2", DBhelper.cont);
		go1.putExtra("det3", DBhelper.eml);
		go1.putExtra("det4", DBhelper.date);
		go1.putExtra("det5", DBhelper.tme);
		go1.putExtra("det6", DBhelper.img);
		startActivity(go1);
	}
	
}

