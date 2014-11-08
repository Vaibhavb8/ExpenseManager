package com.dvs.expensemanager;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class About extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_about);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.about, menu);
		return true;
	}
	 @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        // Handle item selection
	        switch (item.getItemId()) {
	            case R.id.contact:
	            {
	            	   try {
	            	    getPackageManager().getPackageInfo("com.facebook.katana", 0);
	            	    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/267177360026631"));
	            	    startActivity(intent);
	            	   } catch (Exception e) {
	            		   Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/DigitalVisionStudios"));
	            		   startActivity(intent);
	            	   }
	            	   return true;
	            	}
	            default:
	                return super.onOptionsItemSelected(item);
	        }
			
	 }
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
}
