package com.example.minorproject;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddBirthday extends Activity implements OnClickListener,OnDateSetListener,OnTimeSetListener
{	
		
	 private static int RESULT_LOAD_IMG = 1;
	    String imgDecodableString;
	    String imgDecodableStringNew;
	    Button save,cancel;
	    EditText fn,ln,em,con,dte,alrm;
	    String pick[]={"AM","PM"};
	    Spinner sp;
	    private byte[] img=null;
	    private Calendar cal;
	    private int day;
		private int month;
		private int year;
		private int hrs;
		private int min;
		private int sec;
		private DatePickerDialog datepicker;
		private TimePickerDialog timepicker;
		public String spPick;
		 ImageView imgView;
		public int notImgChanged=0;
	   String imgReset;
	   static final int DATE_DIALOG_ID = 0;
	   static final int TIME_DIALOG_ID = 1;
	    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_birthday);
		
		save=(Button)findViewById(R.id.button1);
		cancel=(Button)findViewById(R.id.button2);
		fn=(EditText)findViewById(R.id.editText1);
		ln=(EditText)findViewById(R.id.editText2);
		em=(EditText)findViewById(R.id.editText3);
		con=(EditText)findViewById(R.id.editText4);
		dte=(EditText)findViewById(R.id.editText5);
		alrm=(EditText)findViewById(R.id.editText6);
		sp=(Spinner)findViewById(R.id.spinneradd);
		
		ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,pick);
		sp.setAdapter(adapter);
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				spPick=sp.getItemAtPosition(arg2).toString();
				
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		save.setOnClickListener(this);
		cancel.setOnClickListener(this);
	
		 cal= Calendar.getInstance();
	        day=cal.get(Calendar.DAY_OF_MONTH);
	        month=cal.get(Calendar.MONTH);
	        year=cal.get(Calendar.YEAR);
	        
	        datepicker= new DatePickerDialog(this,this,year,month,day);
	        
	        dte.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					showDialog(DATE_DIALOG_ID);
					
				}
			});
	        
	        hrs=cal.get(Calendar.HOUR);
	        min=cal.get(Calendar.MINUTE);
	        sec=cal.get(Calendar.SECOND);
	        
	        timepicker= new TimePickerDialog(this, this, hrs, min,true);
	        alrm.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					showDialog(TIME_DIALOG_ID);
					
				}
			});
	        
	        try {
	        	Bitmap bm = BitmapFactory.decodeResource( getResources(), R.drawable.asdddd);
	        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
	       
	        File folder = new File(extStorageDirectory+"/BirthdayBash");
	        if (!folder.exists()) 
	        	{
	        	 folder.mkdirs();
	            }
	       
	       

	      
	        String done = folder.toString();

	        File file = new File(done, "asdddd.jpg");
	        FileOutputStream outStream = null;
			
				outStream = new FileOutputStream(file);
			
			
	        bm.compress(Bitmap.CompressFormat.PNG, 100, outStream);
	        
				outStream.flush();
			
				outStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       
	}
	

	
	 public void loadImagefromGallery(View view) {
	        
	        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
	                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
	        
	        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
	       
	       
	    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_add_birthday, menu);
		return true;
	}
	 @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);
	        try {
	        
	            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
	                    && null != data) {
	        
	 
	                Uri selectedImage = data.getData();
	                String[] filePathColumn = { MediaStore.Images.Media.DATA };
	 
	        
	                Cursor cursor = getContentResolver().query(selectedImage,
	                        filePathColumn, null, null, null);
	        
	                cursor.moveToFirst();
	 
	                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
	                imgDecodableString = cursor.getString(columnIndex);
	                cursor.close();
	                 imgView = (ImageView) findViewById(R.id.imageViewpic);
	                
	                imgView.setImageBitmap(BitmapFactory
	                        .decodeFile(imgDecodableString));
	               
	                
	                notImgChanged=1;
	               
	                Toast.makeText(getApplicationContext(), imgDecodableString, Toast.LENGTH_LONG).show();
	            } else {
	            	
	                Toast.makeText(this, "You haven't picked Image",
	                        Toast.LENGTH_LONG).show();
	            }
	        } catch (Exception e) {
	            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
	                    .show();
	        }
	 
	    }
	

	@Override
	public void onClick(View v) 
	{
		
		if(v.getId()==R.id.button1)
		{
			
			String s1=fn.getText().toString();
			String s2=ln.getText().toString();
			String s3=em.getText().toString();
			String s4=con.getText().toString();
			String s5=dte.getText().toString();
			String s6=alrm.getText().toString()+" "+spPick;
			String s7=alrm.getText().toString();
			if(s1.length()==0||s4.length()==0||s5.length()==0||s6.length()==0)
			{
				Toast.makeText(getApplicationContext(), "Enter the appropriate information!!", Toast.LENGTH_LONG).show();
			}
			else
			{
				DBhelper db= new DBhelper(this);
				if(notImgChanged==0)
				{
					File sdCardRoot = Environment.getExternalStorageDirectory();
					File yourDir = new File(sdCardRoot, "/BirthdayBash");
						imgDecodableStringNew=yourDir.toString()+"/asdddd.jpg";
						db.save(s1,s2,s3,s4,s5,s6,imgDecodableStringNew,s7);
				}
				if(notImgChanged==1)
				{
					db.save(s1,s2,s3,s4,s5,s6,imgDecodableString,s7);
					notImgChanged=0;
				}
				Toast.makeText(getApplicationContext(), "Reminder saved", Toast.LENGTH_LONG).show();
				fn.setText(null);
				ln.setText(null);
				em.setText(null);
				con.setText(null);
				dte.setText(null);
				alrm.setText(null);
				
				if(notImgChanged==1)
				 {
					imgView.setImageBitmap(BitmapFactory
				 
	                        .decodeFile(null));
					imgView.setBackgroundResource(R.drawable.asdddd);
				 }
				if(notImgChanged==0)
				{
					File sdCardRoot = Environment.getExternalStorageDirectory();
					File yourDir = new File(sdCardRoot, "/BirthdayBash");
						imgReset=yourDir.toString()+"/asdddd.jpg";
						 imgView = (ImageView) findViewById(R.id.imageViewpic);
			                
			                imgView.setImageBitmap(BitmapFactory
			                        .decodeFile(imgReset));
						
				}
				
				
			}
			
		}
		if(v.getId()==R.id.button2)
		{
			Intent i= new Intent(this,Reminder.class);
			finish();
			startActivity(i);
		}
		
	}



	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		dte.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
		
	}

	 @Override
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
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		alrm.setText(hourOfDay+":"+(minute));
		
	}



}
