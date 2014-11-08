package com.dvs.expensemanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class SecHomescreen extends Activity {
	
	
@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_sec_homescreen);
		
		 
		Button login = (Button)findViewById(R.id.button1);
		Button signup =(Button)findViewById(R.id.button2);
		login.setOnClickListener(new OnClickListener(){
			 @Override
			public void onClick(View view) {
			      Intent i = new Intent(getApplicationContext(), LoginActivity.class);
			      startActivity(i);
		     }
		     });
		signup.setOnClickListener(new OnClickListener(){
			 @Override
			public void onClick(View view2) {
			      Intent i2 = new Intent(getApplicationContext(), Signup.class);
			      startActivity(i2);
		     }
		     });
	
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
	
