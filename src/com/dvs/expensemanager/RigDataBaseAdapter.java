package com.dvs.expensemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class RigDataBaseAdapter 
{
		static final String DATABASE_NAME = "riget.db";
		static final int DATABASE_VERSION = 1;
		public static final int NAME_COLUMN = 1;
		
		// TODO: Create public field for each column in your table.
		// SQL Statement to create a new database.
		static final String DATABASE_CREATE = "create table "+"RIGEXP"+
		                             "( " +"ID"+" integer primary key autoincrement,"+ "USERNAME  text,DATE date,CATEGORY text,COST integer,Quantity integer,EXPENSE integer); ";
		// Variable to hold the database instance
		public  SQLiteDatabase db3;
		// Context of the application using the database.
		private final Context context3;
		// Database open/upgrade helper
		private DataBaseHelper3 dbHelper3;
		public  RigDataBaseAdapter(Context _context) 
		{
			context3 = _context;
			dbHelper3 = new DataBaseHelper3(context3, DATABASE_NAME, null, DATABASE_VERSION);
		}
		public  RigDataBaseAdapter open() throws SQLException 
		{
			db3 = dbHelper3.getWritableDatabase();
			return this;
		}
		public void close() 
		{
			db3.close();
		}

		public  SQLiteDatabase getDatabaseInstance()
		{
			return db3;
		}

		public void insertEntry(String uName,String Date,String Cat,Integer Cost,Integer Quant, Integer Expense)
		{
	       ContentValues newValues = new ContentValues();
			// Assign values for each row.
			newValues.put("USERNAME", uName);
			newValues.put("DATE",Date);
			newValues.put("CATEGORY", Cat);
			newValues.put("COST", Cost);
			newValues.put("QUANTITY", Quant);
			newValues.put("EXPENSE", Expense);
			
			// Insert the row into your table
			db3.insert("RIGEXP", null, newValues);
			///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
		}
		public void deleteEntry(String Date, String Cat, String Expe)
		{
			//String id=String.valueOf(ID);
		    
		    db3.delete("RIGEXP", "DATE=?  AND CATEGORY=? AND EXPENSE=?", new String[]{Date,Cat,Expe}) ;
	       // Toast.makeText(context, "Number for Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
	        
		}	
		public String getDateEntry(String uName)
		{
			Cursor cursor=db3.query("RIGEXP", null, " USERNAME=?", new String[]{uName}, null, null, null);
	        if(cursor.getCount()<1) // UserName Not Exist
	        {
	        	cursor.close();
	        	return "NOT EXIST";
	        }
		    cursor.moveToFirst();
			String date= cursor.getString(cursor.getColumnIndex("DATE"));
			cursor.close();
			return date;				
		}
		
		public String getunameEntry(String uName)
		{
			Cursor cursor=db3.query("RIGEXP", null, " USERNAME=?", new String[]{uName}, null, null, null);
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
		
				
		public Integer getexpenseEntry(String uName)
		{
			Cursor cursor=db3.query("RIGEXP", null, " USERNAME=?", new String[]{uName}, null, null, null);
	        if(cursor.getCount()<1) // UserName Not Exist
	        {
	        	cursor.close();
	        	return 0;
	        }
		    cursor.moveToFirst();
			Integer Expense= Integer.parseInt(cursor.getString(cursor.getColumnIndex("EXPENSE")));
			cursor.close();
			return Expense;
		}
		
		public String getcatEntry(String uName)
		{
			Cursor cursor=db3.query("RIGEXP", null, " USERNAME=?", new String[]{uName}, null, null, null);
	        if(cursor.getCount()<1) // UserName Not Exist
	        {
	        	cursor.close();
	        	return "NOT EXIST";
	        }
		    cursor.moveToFirst();
			String Cat= cursor.getString(cursor.getColumnIndex("CATEGORY"));
			cursor.close();
			return Cat;
		}
		public void  updateEntry(String uName,String Date,String Cat,Integer Cost,Integer Quant, Integer Expense )
		{
			// Define the updated row content.
			ContentValues updatedValues = new ContentValues();
			// Assign values for each row.
			updatedValues.put("USERNAME", uName);
			updatedValues.put("DATE",Date);
			updatedValues.put("CATEGORY", Cat);
			updatedValues.put("COST", Cost);
			updatedValues.put("QUANTITY", Quant);
			updatedValues.put("EXPENSE",Expense);
			
	        String where="USERNAME = ?";
		    db3.update("RIGEXP",updatedValues, where, new String[]{uName});			   
		}		
		
}