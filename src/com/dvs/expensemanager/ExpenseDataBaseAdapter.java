package com.dvs.expensemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ExpenseDataBaseAdapter 
{
		static final String DATABASE_NAME = "exp.db";
		static final int DATABASE_VERSION = 1;
		public static final int NAME_COLUMN = 1;
		
		// TODO: Create public field for each column in your table.
		// SQL Statement to create a new database.
		static final String DATABASE_CREATE = "create table "+"DAILYEXP"+
		                             "( " +"ID"+" integer primary key autoincrement,"+ "USERNAME  text,DATE date,EXPENSE integer); ";
		// Variable to hold the database instance
		public  SQLiteDatabase db2;
		// Context of the application using the database.
		private final Context context2;
		// Database open/upgrade helper
		private DataBaseHelper2 dbHelper2;
		public  ExpenseDataBaseAdapter(Context _context) 
		{
			context2 = _context;
			dbHelper2 = new DataBaseHelper2(context2, DATABASE_NAME, null, DATABASE_VERSION);
		}
		public  ExpenseDataBaseAdapter open() throws SQLException 
		{
			db2 = dbHelper2.getWritableDatabase();
			return this;
		}
		public void close() 
		{
			db2.close();
		}

		public  SQLiteDatabase getDatabaseInstance()
		{
			return db2;
		}

		public void insertEntry(String uName,String Date, Integer Expense)
		{
	       ContentValues newValues = new ContentValues();
			// Assign values for each row.
			newValues.put("USERNAME", uName);
			newValues.put("DATE",Date);
			newValues.put("EXPENSE", Expense);
			
			// Insert the row into your table
			db2.insert("DAILYEXP", null, newValues);
			///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
		}
		public void deleteEntry(String Date)
		{
			//String id=String.valueOf(ID);
		    
		    db2.delete("DAILYEXP","DATE=?",  new String[]{Date}) ;
	       // Toast.makeText(context, "Number for Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
	        
		}	
		public void updateExpense(String uName,String Date,Integer Expense)
		{
			ContentValues updatedValues = new ContentValues();
			updatedValues.put("DATE",Date);
			updatedValues.put("EXPENSE",Expense);
			String where="USERNAME=? AND DATE = ?";
		    db2.update("DAILYEXP",updatedValues, where, new String[]{uName,Date});	
	
	       // Toast.makeText(context, "Number for Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
	        
		}	
		public String getDateEntry(String uName)
		{
			Cursor cursor=db2.query("DAILYEXP", null, " USERNAME=?", new String[]{uName}, null, null, null);
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
			Cursor cursor=db2.query("DAILYEXP", null, " USERNAME=?", new String[]{uName}, null, null, null);
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
			Cursor cursor=db2.query("DAILYEXP", null, " USERNAME=?", new String[]{uName}, null, null, null);
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
		public Integer getexpensefromdate(String uName,String Date)
		{
			Cursor cursor=db2.query("DAILYEXP", null, " USERNAME=? AND DATE=?", new String[]{uName,Date}, null, null, null);
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
		public void  updateEntry(String uName,String Date, Integer Expense )
		{
			// Define the updated row content.
			ContentValues updatedValues = new ContentValues();
			// Assign values for each row.
			updatedValues.put("USERNAME", uName);
			updatedValues.put("DATE",Date);
			updatedValues.put("EXPENSE",Expense);
			
	        String where="DATE = ?";
		    db2.update("DAILYEXP",updatedValues, where, new String[]{Date});			   
		}		
}

