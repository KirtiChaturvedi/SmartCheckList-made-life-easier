package com.example.minorproject;


import org.w3c.dom.ls.LSInput;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DailyRoutineGirl extends Activity {
	
	ListView l;
	String arr[]={"Pen","Wallet","Keys","Spectators","laptop","charger","handkerchief"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_daily_routine_girl);
		l=(ListView)findViewById(R.id.listroutinegirl);
		ArrayAdapter<String> adp=new ArrayAdapter<String>(this,android.R.layout.select_dialog_multichoice, arr)
				{
	        @Override
	        public View getView(int position, View convertView,
	                ViewGroup parent) {
	            View view =super.getView(position, convertView, parent);

	            TextView textView=(TextView) view.findViewById(android.R.id.text1);

	            /*YOUR CHOICE OF COLOR*/
	            textView.setTextColor(Color.WHITE);

	            return view;
	        }
	    };
		l.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		l.setAdapter(adp);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_daily_routine_girl, menu);
		return true;
		
		
		
		
	}

}
