package com.dvs.expensemanager;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Displaydetlist extends Activity

{
	
	ExpenseDataBaseAdapter exp;
	LoginDataBaseAdapter log;
ListView list;

ArrayAdapter<String> arrayadapter;

ArrayList<String> arraylist = new ArrayList<String>();

@Override

protected void onCreate(Bundle savedInstanceState) {

// TODO Auto-generated method stub

super.onCreate(savedInstanceState);

setContentView(R.layout.activity_displaylist);

list=(ListView)findViewById(R.id.list);
exp=new ExpenseDataBaseAdapter(this);
exp=exp.open();


log=new LoginDataBaseAdapter(this);
log=log.open();

//create object of DataBaseHelper Class

DataBaseHelper2 dbhelper=new DataBaseHelper2(Displaydetlist.this, "exp.db", null, 1);

SQLiteDatabase db =dbhelper.getReadableDatabase();
SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
final String x = preferences.getString("username","");
String sql="select * from DAILYEXP WHERE USERNAME=?";

Cursor cursor =db.rawQuery(sql, new String[]{x});

if(cursor.moveToFirst())

{

do{

//taking all data as per field into variables from table

String Date = cursor.getString(cursor.getColumnIndex("DATE"));

int Exp  =  cursor.getInt(cursor.getColumnIndex("EXPENSE"));

String a = Exp+" on "+Date  ;


//adding data into arraylist

arraylist.add(a);


}while(cursor.moveToNext());//accessing data upto last row from table

}

arrayadapter = new ArrayAdapter<String>( this,R.layout.listrow_group,R.id.firstLine, arraylist );
list.setAdapter(arrayadapter);

/*adding complete arraylist with fetch data on listview using arrayadapter, as it is useful to bind data with component   */




list.setOnItemLongClickListener(new OnItemLongClickListener() {


public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
		final int arg2, long arg3) {
	String str=(String) list.getItemAtPosition(arg2);
	final String substr = str.substring(str.length() - 10);
    
	Builder build = new AlertDialog.Builder(Displaydetlist.this);
	build.setTitle("Expenses of "+substr);
	build.setMessage("Do you want to delete ?");
	build.setPositiveButton("Yes",
			new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog,
						int which) {

					Toast.makeText(
							getApplicationContext(),
							"Expense Deleted", Toast.LENGTH_SHORT).show();
					
					int g=exp.getexpensefromdate(x,substr);
					exp.deleteEntry(substr);
					int p=g+log.getexpendEntry(x);
					
					
					
					log.updateEntry(x,log.getSinlgeEntry(x),p);
					
					 Intent intd = new Intent(getApplicationContext(), Det.class);
				     startActivity(intd);
					
					dialog.cancel();
				}
			});

	build.setNegativeButton("No",
			new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog,
						int which) {
					dialog.cancel();
				}
			});
	AlertDialog alert = build.create();
	alert.show();

	return true;
}
});
}
}
