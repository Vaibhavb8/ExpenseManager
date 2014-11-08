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




public class LoginActivity extends Activity {
	EditText uname,password;
	Button sign_in_button;
	LoginDataBaseAdapter loginDataBaseAdapter;
	public static final String Username="com.dvs.expensemanager.Username";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		
		
		loginDataBaseAdapter=new LoginDataBaseAdapter(this);
	     loginDataBaseAdapter=loginDataBaseAdapter.open();
	     final  EditText uname=(EditText)findViewById(R.id.uname2l);
		    final  EditText password=(EditText)findViewById(R.id.password2l);
		    
			Button sign_in_button=(Button)findViewById(R.id.sign_in_button);
				
			// Set On ClickListener
			sign_in_button.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// get The User name and Password
					String uName=uname.getText().toString();
					final String x =uName;
					String Password=password.getText().toString();
					
					// fetch the Password form database for respective user name
					String storedPassword=loginDataBaseAdapter.getSinlgeEntry(uName);
					
					// check if the Stored password matches with  Password entered by user
					if(Password.equals(storedPassword))
					{

							Intent intent=new Intent(getApplicationContext(),Success.class);
							EditText cuser=(EditText)findViewById(R.id.uname2l);
							String u=cuser.getText().toString();
							intent.putExtra(Username,u);
							startActivity(intent);
							
						
						Toast.makeText(LoginActivity.this, "Congrats : Login Successfull", Toast.LENGTH_SHORT).show();
						Toast.makeText(LoginActivity.this, "Welcome "+x, Toast.LENGTH_LONG).show();
						
						
							
							
						
						
						
					}
					else
					{
						Toast.makeText(LoginActivity.this, "User Name or Password does not match", Toast.LENGTH_SHORT).show();
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
		super.onCreateOptionsMenu(menu);
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
	

