package com.dvs.expensemanager;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("SimpleDateFormat")
public class Ret extends Activity {
	Button btnrAdd;
	
	LoginDataBaseAdapter loginDataBaseAdapter;
	RigDataBaseAdapter rigDataBaseAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_ret);
		
		
		
		loginDataBaseAdapter=new LoginDataBaseAdapter(this);
	    loginDataBaseAdapter=loginDataBaseAdapter.open();
	    
	    rigDataBaseAdapter=new RigDataBaseAdapter(this);
	    rigDataBaseAdapter=rigDataBaseAdapter.open();
	    
        btnrAdd=(Button)findViewById(R.id.addrExp);
    	SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
    	String z = preferences.getString("username","");
		String c = preferences.getString(z+"lcat","");
		String e = preferences.getString(z+"lex","");
		((TextView)findViewById(R.id.lexpenseview)).setText(e);
		

		String ld = preferences.getString(z+"ld","");
		((TextView)findViewById(R.id.catview)).setText(c+"\n"+ld);	   

		TextView btndisp = (TextView)findViewById(R.id.lexpenseview);
		btndisp.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v2) { 
				
				Intent inte=new Intent(getApplicationContext(),Displaylist.class);
				startActivity(inte);
			}});
			
				
				
	}

	
	public void addrexp(View V)
	   {
			final Dialog dialog = new Dialog(Ret.this);
			dialog.setContentView(R.layout.addr);
		    dialog.setTitle("Add Rigorous Expense");
		    
		    
		   
		    final  EditText editTextdate=(EditText)dialog.findViewById(R.id.dateentry);
		    final  EditText editTextcategory=(EditText)dialog.findViewById(R.id.desc);
		    final  EditText editTextprice=(EditText)dialog.findViewById(R.id.cost);
		    final  EditText editTextquant=(EditText)dialog.findViewById(R.id.quant);
		    
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String currentDateandTime = sdf.format(new Date());
		    editTextdate.setText(currentDateandTime);
		    
			Button btnrAdd=(Button)dialog.findViewById(R.id.addrexp);
			SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
			final String x = preferences.getString("username","");
			
			
						
			final SharedPreferences.Editor editor = preferences.edit();
			
			
				
			// Set On ClickListener
			btnrAdd.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// get The User name and Password
					String Date=editTextdate.getText().toString();
					
					String C  = editTextcategory.getText().toString();
					String newData  = C.replaceAll(" ", "");
					String P  = editTextprice.getText().toString();
					String Q  = editTextquant.getText().toString();
					if(Date.equals("")||C.equals("")||P.equals(""))
					{
							Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_SHORT).show();
							return;
					}
					if(Q.equals(""))
					{
						Q="1";
					}
					Integer Price = Integer.parseInt(P);
					Integer Quant = Integer.parseInt(Q);
					Integer Expense = Price*Quant;
					String uName = x;
					editor.putString(x+"lcat",newData);
					editor.putString(x+"ld",Date);
					editor.putString(x+"lex",Integer.toString(Expense));
					editor.commit();
					
					
				//	if(Expend<0)
				//	{
				//		Toast.makeText(getApplicationContext(), "Insufficient Funds", Toast.LENGTH_LONG).show();
				//		dialog.dismiss();
				//		Toast.makeText(getApplicationContext(), "Please Increment your Budget", Toast.LENGTH_LONG).show();
				//	}
					
				//	else
				//	{
					
					
					
						rigDataBaseAdapter.insertEntry(uName, Date, newData, Price,Quant,Expense);
									
						
						
						
							Toast.makeText(Ret.this, "Expense Added", Toast.LENGTH_SHORT).show();
							dialog.dismiss();
							
							Intent intent=new Intent(getApplicationContext(),Ret.class);
							startActivity(intent);
							
							
							
					}
					
					
				//}
			});
			
			dialog.show();
	}
	
	
	int backButtonCount=0;
	@Override
	public void onBackPressed()
	{
	   
		if(backButtonCount >= 1)
	    {
	        Intent intent = new Intent(Intent.ACTION_MAIN);
	        intent.addCategory(Intent.CATEGORY_HOME);
	        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	        startActivity(intent);
	    }
	    else
	    {
	        Toast.makeText(this, "Press the back button once again to exit", Toast.LENGTH_SHORT).show();
	        backButtonCount++;
	    }
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu2, menu);
		return true;
	}
	 @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        // Handle item selection
	        switch (item.getItemId()) {
	            case R.id.logout:
	            	Intent i5 = new Intent(getApplicationContext(), HomeActivity.class);
				      startActivity(i5);
				      SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
						SharedPreferences.Editor editor = preferences.edit();
						editor.putString("username","");
						editor.commit();
						Toast.makeText(getApplicationContext(), "Logout Successful", Toast.LENGTH_LONG).show();
	                return true;
	            case R.id.calc:
	            
	            	 Intent ic= new Intent(); 
				       ic.setClassName("com.android.calculator2","com.android.calculator2.Calculator");
			
				       try {
				          startActivity(ic);
				       } catch (ActivityNotFoundException noSuchActivity) {
				    	   Intent mIntent = getPackageManager().getLaunchIntentForPackage(
				    			    "com.sec.android.app.popupcalculator");

				    	   try{
				    			startActivity(mIntent);
				    	   }
				    	   catch (ActivityNotFoundException noActivity){
				    	   Toast.makeText(getApplicationContext(), "Calculator Not Installed", Toast.LENGTH_LONG).show();
				       }}
				       break;
	            	
	            case R.id.aboutus:
	            	Intent ia =new Intent(getApplicationContext(), About.class);
	            	startActivity(ia);
	            	break;
	            	
	            case R.id.abudget:
	            Intent i6 = new Intent(getApplicationContext(), Addbu.class);
	            	startActivity(i6);
	            	break;
	            	
	            case R.id.tog:
	            	 Intent intd = new Intent(getApplicationContext(), Det.class);
				      startActivity(intd);
				      break;
	            
	            
	           default:
	                return super.onOptionsItemSelected(item);
	        }
			return false;
	 }}