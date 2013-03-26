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
		//setContentView(R.layout.activity_list_names);
		thisActivity = this;
		ArrayList<Item> items = LoginActivity.items;
		
		
		testString ="";
		for(Item i:items){
			testString+= i.getName()+";";
		}
		if(testString.length()>2)
			testString=testString.substring(0,testString.length()-2);
		Toast t = Toast.makeText(this, LoginActivity.accounts.toString(), Toast.LENGTH_LONG);
		t.show();
	
		
		//LinearLayout l = new LinearLayout(this);
		
		LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(this.LAYOUT_INFLATER_SERVICE);
	    RelativeLayout r = (RelativeLayout) layoutInflater.inflate(R.layout.activity_home, null);
	    //working with the view object version of the linear layout
		
	    LinearLayout l = (LinearLayout) r.getChildAt(0);
	    
	    //this is what i was trying to do. this is a trick?: this works if you put a ScrollView outside the linear layout
		//LinearLayout l = findViewById(R.id.outer_linear_layout); ????
        
	    //LinearLayout l = (LinearLayout) findViewById(R.id.outer_linear_layout);
	   
	    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
	    		LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(20, 20, 20, 10);
	    
	    
	    l.setOrientation(LinearLayout.VERTICAL);
		String[] parts = testString.split(";");
		if(parts.length > 0) {
			for(int i =0; i < parts.length; i++) {
				TextView newText = new TextView(this); 
				//newText.setBackgroundColor(Color.parseColor("#0000FF"));
				//newText.setTextColor(#E01B6A);
				newText.setText(parts[i]);
				newText.setTextAppearance(getApplicationContext(), R.style.CodeFont);
				newText.setGravity(0x01); //should do this in the xml folder!?!
				//newText.setStyle(@android:style/CodeFont);    someone said not possible to set style dynamically?
				l.addView(newText,layoutParams);
		}
		}
		
		
		
		Button buttonAddItem = new Button(this);
		buttonAddItem.setBackgroundResource(R.drawable.roundbutton);
		buttonAddItem.setText("Add Item");
		buttonAddItem.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent in = new Intent(thisActivity,AddItemActivity.class);
				startActivity(in);
			}
		});
		l.addView(buttonAddItem, layoutParams);
		Button buttonLogOut = new Button(this);
		buttonLogOut.setBackgroundResource(R.drawable.roundbutton);
		buttonLogOut.setText("Log Out");
		buttonLogOut.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Intent in = new Intent(thisActivity,LoginActivity.class);
				startActivity(in);
			}
		});
		l.addView(buttonLogOut, layoutParams);
		
		
		setContentView(r);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home, menu);
		return true;
	}
	
	
	
	

}

