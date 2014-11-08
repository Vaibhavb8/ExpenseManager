package com.dvs.expensemanager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;




public class Addbu extends Activity {
	
	LoginDataBaseAdapter loginDataBaseAdapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.addb);
		
		
		loginDataBaseAdapter=new LoginDataBaseAdapter(this);
	     loginDataBaseAdapter=loginDataBaseAdapter.open();
	     SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
			final String x = preferences.getString("username","");
			Integer exp = loginDataBaseAdapter.getexpendEntry(x);
			final Integer E=exp;
		    		    
			Button addb=(Button)findViewById(R.id.addbud);
				
			// Set On ClickListener
			addb.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// get The User name and Password
					String bud=((EditText)findViewById(R.id.budgetentry)).getText().toString();
					if(bud.equals("") || bud.equals("0"))
					{
							Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
							return;
					}
					else{
					Integer Budget = Integer.parseInt(bud);
					Integer Expend= E + Budget;
					String uName = x;
					String Password=loginDataBaseAdapter.getSinlgeEntry(uName);
					loginDataBaseAdapter.updateEntry(uName,Password,Expend);
					
							Intent intent=new Intent(getApplicationContext(),Det.class);
							startActivity(intent);
							
						
						Toast.makeText(Addbu.this, "Budget Added", Toast.LENGTH_LONG).show();
						
					}
						
							
							
						
						
						
					
				}
			});
			
	
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
		
	
	

	
}
