package com.dvs.expensemanager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class Success extends Activity {
	LoginDataBaseAdapter loginDataBaseAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){ 
		super.onCreate(savedInstanceState);
		loginDataBaseAdapter=new LoginDataBaseAdapter(this);
	    loginDataBaseAdapter=loginDataBaseAdapter.open();
		
		
		
		setContentView(R.layout.activity_success);
		
		Intent intent=getIntent();
		String u=intent.getStringExtra(LoginActivity.Username);		
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString("username",u);
		editor.commit();
		
		Button det = (Button)findViewById(R.id.det);
		Button ret =(Button)findViewById(R.id.ret);
		det.setOnClickListener(new OnClickListener(){
			 @Override
			public void onClick(View view) {
			      Intent i = new Intent(getApplicationContext(), Det.class);
			      startActivity(i);
		     }
		     });
		ret.setOnClickListener(new OnClickListener(){
			 @Override
			public void onClick(View view2) {
			      Intent i2 = new Intent(getApplicationContext(), Ret.class);
			      startActivity(i2);
		     }
		     });
		
		
		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		loginDataBaseAdapter.close();
		
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
		getMenuInflater().inflate(R.menu.menu, menu);
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
	                
	            case R.id.aboutus:
	            	Intent ia =new Intent(getApplicationContext(), About.class);
	            	startActivity(ia);
	            	return true;
	           default:
	                return super.onOptionsItemSelected(item);
	        }
	 }}