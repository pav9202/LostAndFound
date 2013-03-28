package com.example.lostandfound;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lostandfound.util.Item;

public class HomeActivity extends Activity {

	//split the string. we'll worry about actually retrieving text from additemlost activity later.
	
	public static  String testString = "adam;blake;carl;drew;edwin";
	
	protected Activity thisActivity;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		thisActivity = this;
		Button add = (Button)findViewById(R.id.adding);
		Button filter = (Button)findViewById(R.id.filter);
		Button log = (Button)findViewById(R.id.logout);
		
		add.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(thisActivity, AddItemActivity.class);
				startActivity(intent);
			}
		});
		filter.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(thisActivity, FilterActivity.class);
				startActivity(intent);
			}
		});
		log.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(thisActivity, LoginActivity.class);
				startActivity(intent);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home, menu);
		return true;
	}
	
	
	
	

}

