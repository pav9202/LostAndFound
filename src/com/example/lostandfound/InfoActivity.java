package com.example.lostandfound;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class InfoActivity extends Activity {

	protected Activity thisActivity;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info2);
		thisActivity = this;
		
		Button back = (Button) findViewById(R.id.backButton);
		back.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(thisActivity, LoginActivity.class);
				startActivity(intent);
			}
		});
		
	}
}
