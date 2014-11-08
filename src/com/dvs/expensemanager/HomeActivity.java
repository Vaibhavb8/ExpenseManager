package com.dvs.expensemanager;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_home);
		
		Thread timer = new Thread(){
			@Override
			public void run(){
				try{
					sleep(3000);
				} catch (InterruptedException e){
					e.printStackTrace();
				}finally{
					Intent openHome = new Intent(getApplicationContext(),SecHomescreen.class);
					startActivity(openHome);
				}
			}
		};
		timer.start();
	}
	

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
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
		getMenuInflater().inflate(R.menu.defaultmenu, menu);
		
		return true;
	}

	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
                       
            case R.id.aboutus:
            	Intent ia =new Intent(getApplicationContext(), About.class);
            	startActivity(ia);
            	return true;
           default:
                return super.onOptionsItemSelected(item);
        }
 }}
	
