package com.example.lostandfound;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.lostandfound.util.Item;
import com.example.lostandfound.util.SearchUtility;

public class FilterActivity extends Activity {
	EditText name;
	DatePicker date;
	Spinner category;
	Spinner status;
	Activity thisActivity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filter);
		thisActivity = this;
		name = (EditText)findViewById(R.id.nameOfItem);
		date = (DatePicker)findViewById(R.id.dateOfItem);
		category = (Spinner)findViewById(R.id.spinner1);
		status = (Spinner)findViewById(R.id.spinner1);
		Button submit = (Button)findViewById(R.id.submitItem);
		Button home = (Button)findViewById(R.id.home);
		submit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String dat = (date.getMonth()+"/"+date.getDayOfMonth()+"/"+date.getYear());
			
		LayoutInflater layoutInflater = (LayoutInflater) thisActivity.getSystemService(thisActivity.LAYOUT_INFLATER_SERVICE);
	    LinearLayout r = (LinearLayout) layoutInflater.inflate(R.layout.activity_filter, null);
	    //working with the view object version of the linear layout
		
	    LinearLayout l = (LinearLayout)((ScrollView) r.getChildAt(2)).getChildAt(0);
	    l.removeAllViews();
	    //this is what i was trying to do. this is a trick?: this works if you put a ScrollView outside the linear layout
		//LinearLayout l = findViewById(R.id.outer_linear_layout); ????
        
	    //LinearLayout l = (LinearLayout) findViewById(R.id.outer_linear_layout);
	   
	    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
	    		LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(20, 20, 20, 10);
	    
	    
	    l.setOrientation(LinearLayout.VERTICAL);
	    SearchUtility s = new SearchUtility();
	    ArrayList<Item> parts = new ArrayList<Item>();
	    if(!category.getSelectedItem().toString().trim().equals("NONE")){
	    	parts = s.filterByCategory(category.getSelectedItem().toString(), LoginActivity.itemsFound);
	    }
	    else if(!status.getSelectedItem().toString().trim().equals("NONE")){
	    	parts = s.filterByStatus(status.getSelectedItem().toString(), LoginActivity.itemsFound);
	    }
	    else if(dat.trim().length()>0){
	    	parts = s.filterByDate(dat, LoginActivity.itemsFound);
	    }
		if(parts.size() > 0) {
			for(Item i :parts) {
				TextView newText = new TextView(thisActivity); 
				//newText.setBackgroundColor(Color.parseColor("#0000FF"));
				//newText.setTextColor(#E01B6A);
				newText.setText(i.getName());
				newText.setTextAppearance(getApplicationContext(), R.style.CodeFont);
				newText.setGravity(0x01); //should do this in the xml folder!?!
				//newText.setStyle(@android:style/CodeFont);    someone said not possible to set style dynamically?
				l.addView(newText,layoutParams);
		}
		}
		thisActivity.setContentView(r);

			}});
		
		home.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(thisActivity, HomeActivity.class);
				startActivity(intent);
			}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_filter, menu);
		return true;
	}

}
