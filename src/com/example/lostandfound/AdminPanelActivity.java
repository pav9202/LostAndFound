package com.example.lostandfound;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lostandfound.util.Account;
import com.example.lostandfound.util.Admin;
import com.example.lostandfound.util.DBHelper;
import com.example.lostandfound.util.Item;

public class AdminPanelActivity extends Activity {
	Activity thisOne;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_panel);
		thisOne = this;
		
		final EditText name = (EditText) findViewById(R.id.admin_name_enter);
		Button unlock =  (Button) findViewById(R.id.unlock_account);
		unlock.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				boolean done = false;
				DBHelper wonga = new DBHelper(AdminPanelActivity.this);
				wonga.open();
				for (Account credential : wonga.getAllUsers()) {
					if (credential.getUsername().equals(name.getText().toString().trim())) {
						credential.unlock();
						Toast toast = Toast.makeText(thisOne, credential.getUsername()+" is unlocked.", Toast.LENGTH_LONG);
						toast.show();
						done = true;
					}
				}
				wonga.close();
				if(!done){
					Toast toast = Toast.makeText(thisOne, "Account not found", Toast.LENGTH_SHORT);
					toast.show();
				}
			}
			
		});
		Button remove =  (Button) findViewById(R.id.remove_account);
		remove.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				boolean done = false;
				DBHelper snoopy = new DBHelper(AdminPanelActivity.this);
				snoopy.open();
				for (Account credential : snoopy.getAllUsers()) {
					if (credential.getUsername().equals(name.getText().toString().trim())) {
						snoopy.deleteUser(credential.getUsername());
						//LoginActivity.database.removeUserFromDB(credential.getUsername());
						done = true;
						break;
					}
				}
				snoopy.close();
				if(!done){
					Toast toast = Toast.makeText(thisOne, "Account not found", Toast.LENGTH_SHORT);
					toast.show();
				}
			}
			
		});
		Button remove2 =  (Button) findViewById(R.id.remove_item);
		remove2.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				boolean done = false;
				DBHelper snoopy = new DBHelper(AdminPanelActivity.this);
				snoopy.open();
				for (Item credential : snoopy.getAllItems()) {
					if (credential.getName().equals(name.getText().toString().trim())) {
						snoopy.deleteItem(credential.getName());
						//LoginActivity.database.removeUserFromDB(credential.getUsername());
						done = true;
						break;
					}
				}
				snoopy.close();
				if(!done){
					Toast toast = Toast.makeText(thisOne, "Item not found", Toast.LENGTH_SHORT);
					toast.show();
				}
			}
			
		});
		final EditText pass = (EditText) findViewById(R.id.admin_password);
		Button create =  (Button) findViewById(R.id.create_admin);
		create.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				Admin a = new Admin();
				a.setUsername(name.getText().toString().trim());
				a.setPassword(pass.getText().toString().trim());
				DBHelper pavleen = new DBHelper(AdminPanelActivity.this);
				pavleen.open();
				pavleen.insertUser(a);
				pavleen.close();
				//LoginActivity.database.insertUser(a);
				Toast toast = Toast.makeText(thisOne, a.getUsername()+" created and made an Admin with password: "+a.getPassword(), Toast.LENGTH_LONG);
				toast.show();
			}
		});
		
		Button home =  (Button) findViewById(R.id.go_home);
		home.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				Intent intent = new Intent(thisOne, HomeActivity.class);
				//EditText editText = (EditText) findViewById(R.id.edit_message);
				//String message = editText.getText().toString();
				//intent.putExtra(EXTRA_MESSAGE, mEmail+":"+mPassword);
				startActivity(intent);
			}
		});
		Button all = (Button) findViewById(R.id.Allshow);
		all.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				Intent intent = new Intent(thisOne, All_Items.class);
				//EditText editText = (EditText) findViewById(R.id.edit_message);
				//String message = editText.getText().toString();
				//intent.putExtra(EXTRA_MESSAGE, mEmail+":"+mPassword);
				startActivity(intent);
			}
		});
		
		


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admin_panel, menu);
		return true;
	}

}
