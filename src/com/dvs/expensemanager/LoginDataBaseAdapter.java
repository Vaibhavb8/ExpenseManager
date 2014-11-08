package com.dvs.expensemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class LoginDataBaseAdapter 
{
		static final String DATABASE_NAME = "login.db";
		static final int DATABASE_VERSION = 1;
		public static final int NAME_COLUMN = 1;
		// TODO: Create public field for each column in your table.
		// SQL Statement to create a new database.
		static final String DATABASE_CREATE = "create table "+"LOGIN"+
		                             "( " +"ID"+" integer primary key autoincrement,"+ "USERNAME  text,PASSWORD text,EXPEND integer); ";
		// Variable to hold the database instance
		public  SQLiteDatabase db;
		// Context of the application using the database.
		private final Context context;
		// Database open/upgrade helper
		private DataBaseHelper dbHelper;
		public  LoginDataBaseAdapter(Context _context) 
		{
			context = _context;
			dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		public  LoginDataBaseAdapter open() throws SQLException 
		{
			db = dbHelper.getWritableDatabase();
			return this;
		}
		public void close() 
		{
			db.close();
		}

		public  SQLiteDatabase getDatabaseInstance()
		{
			return db;
		}

		public void insertEntry(String uName,String Password, Integer Expend)
		{
	       ContentValues newValues = new ContentValues();
			// Assign values for each row.
			newValues.put("USERNAME", uName);
			newValues.put("PASSWORD",Password);
			newValues.put("EXPEND", Expend);
			
			// Insert the row into your table
			db.insert("LOGIN", null, newValues);
			///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
		}
		public int deleteEntry(String UserName)
		{
			//String id=String.valueOf(ID);
		    String where="USERNAME=?";
		    int numberOFEntriesDeleted= db.delete("LOGIN", where, new String[]{UserName}) ;
	       // Toast.makeText(context, "Number for Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
	        return numberOFEntriesDeleted;
		}	
		public String getSinlgeEntry(String uName)
		{
			Cursor cursor=db.query("LOGIN", null, " USERNAME=?", new String[]{uName}, null, null, null);
	        if(cursor.getCount()<1) // UserName Not Exist
	        {
	        	cursor.close();
	        	return "NOT EXIST";
	        }
		    cursor.moveToFirst();
			String Password= cursor.getString(cursor.getColumnIndex("PASSWORD"));
			cursor.close();
			return Password;				
		}
		
		public String getunameEntry(String uName)
		{
			Cursor cursor=db.query("LOGIN", null, " USERNAME=?", new String[]{uName}, null, null, null);
	        if(cursor.getCount()<1) // UserName Not Exist
	        {
	        	cursor.close();
	        	return "NOT EXIST";
	        }
		    cursor.moveToFirst();
			String Uname= cursor.getString(cursor.getColumnIndex("USERNAME"));
			cursor.close();
			return Uname;				
		}
		
		public String getid(String uName)
		{
			Cursor cursor=db.query("LOGIN", null, " USERNAME=?", new String[]{uName}, null, null, null);
	        if(cursor.getCount()<1) // UserName Not Exist
	        {
	        	cursor.close();
	        	return "NOT EXIST";
	        }
		    cursor.moveToFirst();
			String id= cursor.getString(cursor.getColumnIndex("ID"));
			cursor.close();
			return id;				
		}
		
		public Integer getexpendEntry(String uName)
		{
			Cursor cursor=db.query("LOGIN", null, " USERNAME=?", new String[]{uName}, null, null, null);
	        if(cursor.getCount()<1) // UserName Not Exist
	        {
	        	cursor.close();
	        	return 0;
	        }
		    cursor.moveToFirst();
			Integer Expend= Integer.parseInt(cursor.getString(cursor.getColumnIndex("EXPEND")));
			cursor.close();
			return Expend;
		}
		public void  updateEntry(String uName,String Password, Integer Expend )
		{
			// Define the updated row content.
			ContentValues updatedValues = new ContentValues();
			// Assign values for each row.
			updatedValues.put("USERNAME", uName);
			updatedValues.put("PASSWORD",Password);
			updatedValues.put("EXPEND",Expend);
			
	        String where="USERNAME = ?";
		    db.update("LOGIN",updatedValues, where, new String[]{uName});			   
		}		
}

