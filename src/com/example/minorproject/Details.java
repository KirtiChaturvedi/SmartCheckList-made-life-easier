package com.example.minorproject;



import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Details extends Activity 
{
	TextView t1,t2,t3,t4,t5,tls;
	ImageView imgv;
	String s1,s2,s3,s4,s5,sls,s6;
	String imgstr;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		
		t1=(TextView)findViewById(R.id.textView2);
		t2=(TextView)findViewById(R.id.textView4);
		t3=(TextView)findViewById(R.id.textView6);
		t4=(TextView)findViewById(R.id.textView8);
		t5=(TextView)findViewById(R.id.textView11);
		tls=(TextView)findViewById(R.id.textViewls);
		
		imgv=(ImageView)findViewById(R.id.imageView11);
		 
		

		Intent det= getIntent();
		 s1=det.getStringExtra("det1");
		t1.setText(s1);
		sls=det.getStringExtra("detls");
		tls.setText(sls);
		
		 s2=det.getStringExtra("det2");
		t2.setText(s2);
		 s3=det.getStringExtra("det3");
		t3.setText(s3);
		 s4=det.getStringExtra("det4");
		t4.setText(s4);
		 s5=det.getStringExtra("det5");
		t5.setText(s5);
		 imgstr=det.getStringExtra("det6");
		 imgv.setImageBitmap(BitmapFactory.decodeFile(imgstr));
		 s6=det.getStringExtra("det7");
		 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_details, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId())
		{
		case R.id.editdetailbdy:
			finish();
			Intent ee=new Intent(getApplicationContext(),EditReminder.class);
			ee.putExtra("val1", s1);
			ee.putExtra("val2", sls);
			ee.putExtra("val3", s2);
			ee.putExtra("val4", s3);
			ee.putExtra("val5", s4);
			ee.putExtra("val6", s6);
			ee.putExtra("val7", imgstr);
		
			startActivity(ee);
			return true;
		case R.id.deletedetailbdy :
			AlertDialog.Builder adb= new AlertDialog.Builder(Details.this);
			adb.setTitle("Delete Birthday ?");
			adb.setMessage("Do you really want to delete this entry?");
			adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					DBhelper db= new DBhelper(getApplicationContext());
					db.delete(s1);
					Toast.makeText(getApplicationContext(), "Reminder Deleted",Toast.LENGTH_SHORT).show();
					finish();
					Intent din= new Intent(getApplicationContext(),Reminder.class);
					startActivity(din);
					
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
		case R.id.calldetailbdy:
			Intent callintent= new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+s3));
			startActivity(callintent);
			return true;
		case R.id.newdetailbdy:
			finish();
			Intent newdetail=new Intent(this,AddBirthday.class);
			
			startActivity(newdetail);
			return true;
			default :
				return super.onOptionsItemSelected(item);
	     }
	

}
}
