package com.example.minorproject;


import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class EditReminder extends Activity implements OnClickListener,OnDateSetListener,OnTimeSetListener
{
	EditText fn,ln,ml,con,dte,tme;
	Button edit,cancel;
	Spinner sp;
	String spnr[]={"AM","PM"};
	public   String spn;
	String s1,s2,s3,s4,s5,s6;
	private Calendar cal;
    private int day;
	private int month;
	private int year;
	private int hrs;
	private int min;
	private int sec;
	private DatePickerDialog datepicker;
	private TimePickerDialog timepicker;
	static final int DATE_DIALOG_ID = 0;
	   static final int TIME_DIALOG_ID = 1;
	   ImageView imgView;
	 String u1; 
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_reminder);
		
		fn=(EditText)findViewById(R.id.editText1);
		ln=(EditText)findViewById(R.id.editText2);
		ml=(EditText)findViewById(R.id.editText3);
		con=(EditText)findViewById(R.id.editText4);
		dte=(EditText)findViewById(R.id.editText5);
		tme=(EditText)findViewById(R.id.editText6);
		sp=(Spinner)findViewById(R.id.spinner1);
		imgView=(ImageView)findViewById(R.id.imageViewpic);
		
		edit=(Button)findViewById(R.id.button1);
		cancel=(Button)findViewById(R.id.button2);
		
		edit.setOnClickListener(this);
		cancel.setOnClickListener(this);
		
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,spnr);
		sp.setAdapter(adapter);
		sp.setOnItemSelectedListener(new OnItemSelectedListener() 
		{

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) 
			{
				spn=sp.getItemAtPosition(arg2).toString();
				
			}


		

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		cal= Calendar.getInstance();
        day=cal.get(Calendar.DAY_OF_MONTH);
        month=cal.get(Calendar.MONTH);
        year=cal.get(Calendar.YEAR);
        
        datepicker= new DatePickerDialog(this,this,year,month,day);
        dte.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				showDialog(DATE_DIALOG_ID);
				
				
			}
		});
       
        
        hrs=cal.get(Calendar.HOUR);
        min=cal.get(Calendar.MINUTE);
        sec=cal.get(Calendar.SECOND);
        
        timepicker= new TimePickerDialog(this, this, hrs, min,true);
        tme.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
					showDialog(TIME_DIALOG_ID);
			}
		});
		Intent edt=getIntent();
		s1=edt.getStringExtra("val1");
		fn.setText(s1);
		s2=edt.getStringExtra("val2");
		ln.setText(s2);
		s3=edt.getStringExtra("val3");
		ml.setText(s3);
		s4=edt.getStringExtra("val4");
		con.setText(s4);
		s5=edt.getStringExtra("val5");
		dte.setText(s5);
		s6=edt.getStringExtra("val6");
		tme.setText(s6);
		String imgstr=edt.getStringExtra("val7");
		imgView.setImageBitmap(BitmapFactory.decodeFile(imgstr));
		u1=s1;
	}

	

	@Override
	public void onClick(View v) 
	{
		if(v.getId()==R.id.button1)
		{
			 String s1=fn.getText().toString();
			 String s2=ln.getText().toString();
			 String s3=ml.getText().toString();
			 String s4=con.getText().toString();
			 String s5=dte.getText().toString();
			 String s6=tme.getText().toString()+" "+spn;
			 String s7=tme.getText().toString();
			DBhelper obj=new DBhelper(this);
			obj.update(s1, s2, s3, s4, s5, s6,s7,u1);
			Toast.makeText(getApplicationContext(), "Reminder updated", Toast.LENGTH_SHORT).show();
			finish();
			Intent m=new Intent(this,Reminder.class);
			startActivity(m);
		}
		
	if(v.getId()==R.id.button2)
	{
		finish();
		Intent i=new Intent(this,Reminder.class);
		startActivity(i);
	}
	}
	
	protected Dialog onCreateDialog(int id)
	 {
     switch (id) 
     {
     case DATE_DIALOG_ID:
         return datepicker;
     case TIME_DIALOG_ID:
         return timepicker;
     }
     return null;
 }

	@Override
	public void onDateSet(DatePicker arg0, int year, int monthOfYear, int dayOfMonth) 
	{
		dte.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
		
		
	}

	@Override
	public void onTimeSet(TimePicker arg0, int hourOfDay, int minute) 
	{
		tme.setText(hourOfDay+":"+(minute));		
	}


	}

	

