package com.dvs.expensemanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends Activity {
	EditText uname,password,cpassword,expend;
	Button sign_up_button;
	
	LoginDataBaseAdapter loginDataBaseAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_signup);
		// get Instance  of Database Adapter
				loginDataBaseAdapter=new LoginDataBaseAdapter(this);
				loginDataBaseAdapter=loginDataBaseAdapter.open();
				
				// Get References of Views
				uname=(EditText)findViewById(R.id.uname);
				password=(EditText)findViewById(R.id.password);
				cpassword=(EditText)findViewById(R.id.cpassword);
				expend=(EditText)findViewById(R.id.expend);
				sign_up_button=(Button)findViewById(R.id.sign_up_button);
				sign_up_button.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						String uName=uname.getText().toString();
						String Password=password.getText().toString();
						String Cpassword=cpassword.getText().toString();
						String E=expend.getText().toString();
						
						//check if account already exists
						if(uName.equals(loginDataBaseAdapter.getunameEntry(uName))){
							Toast.makeText(getApplicationContext(), "Username Already Exists", Toast.LENGTH_LONG).show();
							return;
						}
						// check if any of the fields are vaccant
						if(uName.equals("")||Password.equals("")||Cpassword.equals("")||E.equals(""))
						{
								Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
								return;
						}
						
						Integer Expend=Integer.parseInt(E);
						// check if both password matches
						if(!Password.equals(Cpassword))
						{
							Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
							return;
						}
						else
						{
						    // Save the Data in Database
						    loginDataBaseAdapter.insertEntry(uName, Password, Expend);
						    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
						    
						   
								      Intent i3 = new Intent(getApplicationContext(), SecHomescreen.class);
								      startActivity(i3);
							     
						}
					}
				});
			}
				@Override
				protected void onDestroy() {
					// TODO Auto-generated method stub
					super.onDestroy();
					
					loginDataBaseAdapter.close();
				}

			
				@Override
				protected void onPause() {
					// TODO Auto-generated method stub
					super.onPause();
					finish();
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
	
