package com.example.minorproject;
import java.io.ByteArrayInputStream;

import org.apache.http.util.ByteArrayBuffer;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class HomePageMain extends Activity implements OnClickListener{
	ImageView boy,girl,aapp,aus;
	ImageButton browseboy,browsegirl;
	 private static int RESULT_LOAD_IMAGE = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_page_main);
		boy=(ImageView)findViewById(R.id.Boys);
		girl=(ImageView)findViewById(R.id.Girls);
		aapp=(ImageView)findViewById(R.id.abtapp);
		aus=(ImageView)findViewById(R.id.abtus);
		browseboy=(ImageButton)findViewById(R.id.browseboy);
		browsegirl=(ImageButton)findViewById(R.id.browsegirl);
		boy.setOnClickListener(this);
		girl.setOnClickListener(this);
		aapp.setOnClickListener(this);
		aus.setOnClickListener(this);
		browseboy.setOnClickListener(this);
		browsegirl.setOnClickListener(this);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home_page_main, menu);
		return true;
	}
@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.Boys)
		{
			Intent i=new Intent(this,BoyActivity.class);
			startActivity(i);
		}
		if(v.getId()==R.id.Girls)
		{
			Intent i=new Intent(this,GirlActivity.class);
			startActivity(i);
		}
		if(v.getId()==R.id.browsegirl)
		{
			Intent i = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			i.putExtra("ivalue", "girl");
			startActivityForResult(i, RESULT_LOAD_IMAGE);
		}
		if(v.getId()==R.id.browseboy)
		{
			Intent i = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			i.putExtra("ivalue","boy");
			startActivityForResult(i, RESULT_LOAD_IMAGE);
		}
		if(v.getId()==R.id.abtus)
		{
			Intent i=new Intent(this,Aboutus.class);
			startActivity(i);
		}
		if(v.getId()==R.id.abtapp)
		{
			Intent i=new Intent(this,Aboutapp.class);
			startActivity(i);
		}
		}

protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	Intent i=new Intent();
	String s1=i.getStringExtra("iname");
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
        Uri selectedImage = data.getData();
        String[] filePathColumn = { MediaStore.Images.Media.DATA };

        Cursor cursor = getContentResolver().query(selectedImage,
                filePathColumn, null, null, null);
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        ImageView imageView;
//		if(s1.equals("boy"))
//        imageView = (ImageView) findViewById(R.id.Boys);
//        else
        imageView = (ImageView) findViewById(R.id.Girls);	
        imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

    }
}
}
