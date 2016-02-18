package com.example.minorproject;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class CustomList extends ArrayAdapter<String>{
 
private final Activity context;
private final ArrayList<String> web;
private final ArrayList<String> img;
private final ArrayList<String> dte;
private final ArrayList<String> alrm;
private final ArrayList<String> lst;
public static int indx;

	public CustomList(Activity context,ArrayList<String> web, ArrayList<String> img,ArrayList<String> dte, ArrayList<String> alrm, ArrayList<String> lst)
	{
	super(context, R.layout.single_list,web);
	this.context = context;
	this.web = web;
	this.img = img;
	this.dte = dte;
	this.alrm=alrm;
	this.lst=lst;
	 
	}
		@Override
		public View getView(int position, View view, ViewGroup parent)
		{
			indx=position;
				LayoutInflater inflater = context.getLayoutInflater();
				View rowView= inflater.inflate(R.layout.single_list, null, true);
				TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
				TextView txtDate = (TextView) rowView.findViewById(R.id.textViewdate);
				TextView txtAlrm = (TextView) rowView.findViewById(R.id.textViewalrm);
				TextView txtlst = (TextView) rowView.findViewById(R.id.textViewlst);
				ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
				

				txtTitle.setText(web.get(position));
				txtDate.setText(dte.get(position));
				txtAlrm.setText(alrm.get(position));
				txtTitle.setText(web.get(position));
				txtlst.setText(lst.get(position));
				
				
					 
		                imageView.setImageBitmap(BitmapFactory.decodeFile(img.get(position)));
				
				return rowView;
		}
		}
