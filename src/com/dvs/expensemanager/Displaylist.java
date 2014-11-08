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

public class Displaylist extends Activity

{
	RigDataBaseAdapter rig;
	
	
	
ListView list;


ArrayAdapter<String> arrayadapter;

ArrayList<String> arraylist = new ArrayList<String>();

@Override

protected void onCreate(Bundle savedInstanceState) {

// TODO Auto-generated method stub

super.onCreate(savedInstanceState);

setContentView(R.layout.activity_displaylist);

list=(ListView)findViewById(R.id.list);
rig=new RigDataBaseAdapter(this);
rig=rig.open();




//create object of DataBaseHelper Class

DataBaseHelper3 dbhelper=new DataBaseHelper3(Displaylist.this, "riget.db", null, 1);

SQLiteDatabase db =dbhelper.getReadableDatabase();
SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
final String z = preferences.getString("username","");

String sql="select * from RIGEXP WHERE USERNAME=?";

Cursor cursor =db.rawQuery(sql, new String[]{z});

if(cursor.moveToFirst())

{

do{

//taking all data as per field into variables from table

String Cat = cursor.getString(cursor.getColumnIndex("CATEGORY"));

String Date = cursor.getString(cursor.getColumnIndex("DATE"));

int Exp  =  cursor.getInt(cursor.getColumnIndex("EXPENSE"));

String a = Exp+" on "+Cat+"\n"+Date  ;


//adding data into arraylist

arraylist.add(a);


}while(cursor.moveToNext());//accessing data upto last row from table

}

arrayadapter = new ArrayAdapter<String>( this,R.layout.listrow_group,R.id.firstLine, arraylist );
list.setAdapter(arrayadapter);

/*adding complete arraylist with fetch data on listview using arrayadapter, as it is useful to bind data with component   */


final String c = preferences.getString(z+"lcat","");
final String e = preferences.getString(z+"lex","");
final String ld = preferences.getString(z+"ld","");
final SharedPreferences.Editor editor = preferences.edit();


list.setOnItemLongClickListener(new OnItemLongClickListener() {


public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
		final int arg2, long arg3) {
String str=(String) list.getItemAtPosition(arg2);
	
	final String[] substr = str.split("\\s+");
    
	Builder build = new AlertDialog.Builder(Displaylist.this);
	build.setTitle("Expenses of "+substr[2]);
	build.setMessage("Do you want to delete ?");
	build.setPositiveButton("Yes",
			new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface dialog,
						int which) {

					Toast.makeText(
							getApplicationContext(),
							"Expense Deleted", Toast.LENGTH_SHORT).show();
					
					final String exp= substr[0];
					final String date= substr[substr.length-1];
					String cat= substr[2];
					rig.deleteEntry(date,cat,exp);
													
					
					
					if(ld.equals(date) && c.equals(cat) && e.equals(exp))
					{
						
						editor.putString(z+"lcat","");
						editor.putString(z+"ld","");
						editor.putString(z+"lex","");	
						editor.commit();
					}
					
					Intent intr = new Intent(getApplicationContext(), Ret.class);
				     startActivity(intr);
					
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