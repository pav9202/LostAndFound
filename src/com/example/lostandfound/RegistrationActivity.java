package com.example.lostandfound;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.lostandfound.util.DBHelper;
import com.example.lostandfound.util.User;

public class RegistrationActivity extends Activity {
    public final static String EXTRA_MESSAGE = "com.example.lostandfound.RegistrationActivity.NEWACCOUNT";
	private Activity thisActivity;
	private String message;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		thisActivity = this;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		Intent intent = getIntent();
		message = intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);
		Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
		toast.show();
		getMenuInflater().inflate(R.menu.activity_registration, menu);
		
		Button b = (Button) findViewById(R.id.dummy_button);
		OnClickListener list = new OnClickListener(){
			public void onClick(View v){
				// TODO saving to the database here
				Intent in = new Intent(thisActivity,HomeActivity.class);
				User newUser = new User();
				String[] s = message.trim().split(":");
				newUser.setUsername(s[0]);
				newUser.setPassword(s[1]);
				newUser.setType("User");
				newUser.setID(newUser.getUsername().hashCode()+"");
				DBHelper xd = new DBHelper(thisActivity);
				xd.open();
				xd.insertUser(newUser);
				xd.close();
				//LoginActivity.database.insertUser(newUser);
				LoginActivity.curAcc = newUser;
				startActivity(in);
			}
		};
		b.setOnClickListener(list);
		
		
		
//		Button testingHomeActivity = (Button) findViewById(R.id.home_activity_button);
//		OnClickListener goHomeActivityClick = new OnClickListener(){
//			public void onClick(View v) {
//				Intent in = new Intent(thisActivity,HomeActivity.class);
//				startActivity(in);
//			}
//		};
//		testingHomeActivity.setOnClickListener(goHomeActivityClick);
		
		
		return true;
	}
	

}
