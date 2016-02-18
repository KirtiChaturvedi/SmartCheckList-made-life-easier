package com.example.minorproject;


import java.util.ArrayList;


import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;

public class Upcoming extends Activity implements OnItemClickListener
{	
	 ListView l;
	 String selectvalue ;
	CustomList cust;
	String s;
	BirthService obj;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upcoming);
		 l=(ListView)findViewById(R.id.listViewup);
		DBhelper db= new DBhelper(this);
		
		ArrayList<String> list= db.upcoming();
		ArrayList<String> list1= db.upcomingImage();
		ArrayList<String> list2= db.upcomingDate();
		ArrayList<String> list3= db.upcomingAlrm();
		ArrayList<String> list4= db.upcominglst();
		
		
		 cust = new CustomList(this, list, list1,list2,list3,list4);
	       
		
	          l.setAdapter(cust);    
	          l.setOnItemClickListener(this);
	          registerForContextMenu(l);      


	          
	         
	    
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) {
		case R.id.editup:
			Toast.makeText(getApplicationContext(), "select any reminder",Toast.LENGTH_SHORT).show();
			return true;
		case R.id.searchup:
			finish(); 
			Intent s=new Intent(getApplicationContext(),Search.class);
			startActivity(s);
			return true;
		case R.id.deleteup:
			
			AlertDialog.Builder adb= new AlertDialog.Builder(Upcoming.this);
			adb.setTitle("Delete All reminders ?");
			adb.setMessage("All reminders will be deleted.");
			adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
					DBhelper db= new DBhelper(getApplicationContext());
					db.deleteAll();
					Toast.makeText(getApplicationContext(), "All Reminders Deleted", Toast.LENGTH_LONG).show();
					Intent del= new Intent(getApplicationContext(),Upcoming.class);
					startActivity(del);
				}
			});
			adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() 
			{
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
					
				}
			});
			AlertDialog ad=adb.create();
			ad.show();
			
			
			return true;
		case R.id.deleup:
			Toast.makeText(getApplicationContext(), "select any reminder",Toast.LENGTH_SHORT).show();
			return true;
		case R.id.addnewup:
			finish();
			Intent ADD=new Intent(getApplicationContext(),AddBirthday.class);
			startActivity(ADD);
			
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_upcoming, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		 selectvalue = cust.getItem(position);
		Toast.makeText(this,selectvalue,Toast.LENGTH_SHORT).show();
		finish();
		Intent go =new Intent(this,Details.class);
		DBhelper db= new DBhelper(this);
		db.detailbdy(selectvalue);
		go.putExtra("det1",DBhelper.nme);
		go.putExtra("detls",DBhelper.lastn );
		go.putExtra("det2", DBhelper.cont);
		go.putExtra("det3", DBhelper.eml);
		go.putExtra("det4", DBhelper.date);
		go.putExtra("det5", DBhelper.tme);
		go.putExtra("det6", DBhelper.img);
		go.putExtra("det7", DBhelper.tme1);
		startActivity(go);
	}	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.setHeaderTitle("Select the action ");
		menu.add(0, v.getId(), 0, "Message");
		menu.add(0, v.getId(), 1, "Email");
		menu.add(0, v.getId(), 2, "Call");
		menu.add(0, v.getId(), 3, "Delete/Edit");
	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		
		AdapterContextMenuInfo info=(AdapterContextMenuInfo)item.getMenuInfo();
		int listposition=info.position;
		 s=l.getItemAtPosition(listposition).toString();
		
	
		if(item.getTitle()=="Message")
		{
			Intent ms=new Intent(Intent.ACTION_VIEW);
			ms.putExtra("SmsBody", "write your message here");
			ms.setType("vnd.android-dir/mms-sms");
			startActivity(ms);
		}
		if(item.getTitle()=="Email")
		{
			 Intent email = new Intent(Intent.ACTION_SEND);
			 email.setType("message/rfc822");
             startActivity(Intent.createChooser(email,"Choose an Email client :"));
		}
		if(item.getTitle()=="Call")
		{
			DBhelper db= new DBhelper(this);
			db.getcontext(s);
			Intent callcontext= new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+DBhelper.contextphone));
			startActivity(callcontext);
			
		}
		
		if(item.getTitle()=="Delete/Edit")
		{
			Toast.makeText(this, "Select any contact", Toast.LENGTH_LONG).show();
		}
		return true;
	}

}
