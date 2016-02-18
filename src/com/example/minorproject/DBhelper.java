package com.example.minorproject;


import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

public class DBhelper extends SQLiteOpenHelper
{
	public static String dbName="birthday1.db";
	public static String tableName="information";
	public static int dbVersion=1;
	public static String createTable="create table information(_id integer primary key autoincrement,firstname text,lastname text,email textEmailAddress,contact phone, calendar text,alarm text,profile text,alarmchck text )";
	public static String column1="firstname";
	public static String column2="lastname";
	public static String column3="email";
	public static String column4="contact";
	public static String column5="calendar";
	public static String column6="alarm";
	public static String column7="profile";
	public static String column8="alarmchck";
	public static String TAG="DB_bday";
	
	SQLiteDatabase db;
	
	public static String contextphone;
	
	public static String nme;
	public static String lastn;
	public static String cont;
	public static String eml;
	public static String date;
	public static String tme;
	public static String tme1;
	public static String img;
	public static int count=0;

	public DBhelper(Context c)
	{
		super(c,dbName,null,dbVersion);
		Log.i(TAG, "Database created!!");
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(createTable);
		Log.i(TAG, "table created!!!");
		
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public void save(String s1, String s2, String s3, String s4, String s5, String s6,String s7,String s8) 
	{
		db=getWritableDatabase();
		ContentValues cv= new ContentValues();
		cv.put(column1, s1);
		cv.put(column2, s2);
		cv.put(column3, s3);
		cv.put(column4, s4);
		cv.put(column5, s5);
		cv.put(column6, s6);
		cv.put(column7, s7);
		cv.put(column8, s8);
		
		db.insert(tableName, null, cv);
		db.close();

	}
	
	public ArrayList<String> upcoming()
	{
		ArrayList<String> list= new ArrayList<String>();
		db=getWritableDatabase();
		Cursor cur=db.rawQuery("select * from information order by  calendar ", null);
		
		while(cur.moveToNext())
		{
			//StringBuffer sb= new StringBuffer();
			//order by  strftime('%s', calendar) DESC
			int id=cur.getInt(0);
			String fm=cur.getString(1);
			String ln=cur.getString(2);
			String sb=new String(fm);
			if(id>=1)
			{
				count=1;
			}
			else
			{
				count=0;
			}
			//sb.append(fm);
			list.add(sb);
			
			
		}
		
		db.close();
		return list;
		
	}

	public ArrayList<String> upcominglst()
	{
		ArrayList<String> list4= new ArrayList<String>();
		db=getWritableDatabase();
		Cursor cur=db.rawQuery("select * from information order by  calendar ", null);
		
		while(cur.moveToNext())
		{
			//StringBuffer sb= new StringBuffer();
			//order by  strftime('%s', calendar) DESC
			//int id=cur.getInt(0);
			String fm=cur.getString(1);
			String ln=cur.getString(2);
			String sb=new String(ln);
			
			//sb.append(fm);
			list4.add(sb);
			
			
		}
		
		db.close();
		return list4;
		
	}

	public ArrayList<String> upcomingImage() {

		ArrayList<String> list1= new ArrayList<String>();
		db=getWritableDatabase();
		Cursor cur=db.rawQuery("select * from information order by  calendar ", null);
		
		while(cur.moveToNext())
		{
			
			//int id=cur.getInt(0);
			
			String pro=cur.getString(7);
			//sb.append(pro);
			String sb= new String(pro);
			list1.add(sb);
			
		}
		
		db.close();
		return list1;
	}


	public ArrayList<String> upcomingDate() {
		ArrayList<String> list2= new ArrayList<String>();
		db=getWritableDatabase();
		Cursor cur=db.rawQuery("select * from information order by calendar ", null);
		
		while(cur.moveToNext())
		{
			
			//int id=cur.getInt(0);
			
			String dte=cur.getString(5);
			//sb.append(pro);
			String sb= new String(dte);
			list2.add(sb);
			
		}
		
		db.close();
		return list2;
	}


	public ArrayList<String> upcomingAlrm() {
		ArrayList<String> list3= new ArrayList<String>();
		db=getWritableDatabase();
		Cursor cur=db.rawQuery("select * from information order by calendar ", null);
		
		while(cur.moveToNext())
		{
			
			//int id=cur.getInt(0);
			
			String alrm=cur.getString(6);
			//sb.append(pro);
			String sb= new String(alrm);
			list3.add(sb);
			
		}
		
		db.close();
		return list3;
	}



	public void detailbdy(String s1)
	{
		String valuedd[]={s1};
		db=getWritableDatabase();
		Cursor cur=db.rawQuery("select * from information where firstname = ? " ,valuedd);
		while(cur.moveToNext())
		{
			
		
			 nme=cur.getString(1);
			lastn=cur.getString(2);
			 cont=cur.getString(3);
			 eml=cur.getString(4);
			date=cur.getString(5);
			tme=cur.getString(6);
			img=cur.getString(7);
			tme1=cur.getString(8);
		
			
		}
		db.close();
	}


	public void delete(String s1) {
		String val1[]={s1};
		db=getWritableDatabase();
		db.delete(tableName, "firstname=?", val1);
		db.close();
		
	}


	public void update(String s1, String s2, String s3, String s4, String s5,String s6, String s7 ,String u1) 
	{
		db=getWritableDatabase();
		String val[]={u1};
		ContentValues cv= new ContentValues();
		cv.put(column1, s1);
		cv.put(column2, s2);
		cv.put(column3, s3);
		cv.put(column4, s4);
		cv.put(column5, s5);
		cv.put(column6, s6);
		cv.put(column8, s7);
		db.update(tableName, cv, "firstname=?", val);
		db.close();
		
	}


	public void deleteAll() {
		String value[]={};
		db=getWritableDatabase();
		db.delete(tableName, "1", value);
		db.close();
		
	}
	public String checkDate(String currDate)
	{
		String current[]={currDate};
		String sb = null;
		db=getWritableDatabase();
		Cursor cur=db.rawQuery("select * from information where calendar=?", current);
		
		while(cur.moveToNext())
		{
			String chckdte=cur.getString(5);
			//sb.append(pro);
			 sb= new String(chckdte);
			
			
		}
		
		db.close();
		return sb;
	}

	public String checkTime(String currTime)
	{
		String currentTme[]={currTime};
		String sb = null;
		db=getWritableDatabase();
		Cursor cur=db.rawQuery("select * from information where alarmchck=?", currentTme);
		
		while(cur.moveToNext())
		{
			String chckTme=cur.getString(8);
			//sb.append(pro);
			 sb= new String(chckTme);
			
			
		}
		
		db.close();
		return sb;
	}


	public void getcontext(String s) {
		String getcon[]={s};
		db=getWritableDatabase();
		Cursor curs=db.rawQuery("select * from information where firstname= ?",getcon );
		while(curs.moveToNext())
		{
		
			 contextphone=curs.getString(4);
		
			
		}
		db.close();
		
	}


	}
	



	
