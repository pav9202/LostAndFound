package com.example.lostandfound;

import java.io.IOException;
import java.util.ArrayList;

import com.example.lostandfound.util.DBHelper;
//import com.example.lostandfound.database.Database;
import com.example.lostandfound.util.Item;
//import com.example.lostandfound.util.ItemDatabaseFoundUtility;
import com.example.lostandfound.util.User;

import android.os.Bundle;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SearchItemActivity extends Activity {

	//our database is LoginActivity.database
	
	LayoutInflater layoutInflater;
	
    RelativeLayout r;
    LinearLayout newLayout;
    ScrollView bottom_scroll_view;
    
    EditText nameEditText, descriptionEditText;
    TextView rowName, rowDescription, rowCategory, rowStatus, rowDate, rowUserId, nameTextView, descriptionTextView, categoryTextView,
                     statusTextView, dateTextView, useridTextView;
	Spinner categorySpinner, locationSpinner, statusSpinner, daySpinner, monthSpinner, yearSpinner;
    Button update, returnButton;
    
    String nameText, descriptionText, categoryUpdate, locationUpdate, statusUpdate, dayUpdate, monthUpdate, yearUpdate,
                     name, description, category, status, date, userid, dateUpdate;
    public static Activity thisActivity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_item);
		
		thisActivity = this;
		
		layoutInflater = (LayoutInflater) thisActivity.getSystemService(thisActivity.LAYOUT_INFLATER_SERVICE);
	    r = (RelativeLayout) layoutInflater.inflate(R.layout.activity_search_item, null);
	    bottom_scroll_view = (ScrollView) r.getChildAt(1);
	    
	    //set the edittexts
	    nameEditText = (EditText) findViewById(R.id.name);
		descriptionEditText = (EditText) findViewById(R.id.description);
		//set the spinners
		categorySpinner = (Spinner) findViewById(R.id.category_spinner);
	    locationSpinner = (Spinner) findViewById(R.id.location_spinner);
	    statusSpinner = (Spinner) findViewById(R.id.status_spinner);
	    daySpinner = (Spinner) findViewById(R.id.day_spinner);
	    monthSpinner = (Spinner) findViewById(R.id.month_spinner);
	    yearSpinner = (Spinner) findViewById(R.id.year_spinner);
	    //set the buttons
	    update = (Button) findViewById(R.id.update_button);
	    returnButton = (Button) findViewById(R.id.return_home_button);
        

		returnButton.setOnClickListener(
				new OnClickListener(){
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(thisActivity, HomeActivity.class);
						startActivity(intent);
					}
				}
			);
	    
	    
	    update.setOnClickListener(
	    		
	    new OnClickListener(){
	    	@Override
	    	public void onClick(View v) {
	    		
	    		//TWO OPTIONS. Clear the linear layout defined in xml. Or create a new linear layout each time. 2nd option leads to memory leak?
	    		//make sure at the end you know how to display the scrollview/linearlayout that has been updated
	    		
	    		TextView results = (TextView)findViewById(R.id.list);
	    		results.setText("");
	    	    // get edittext strings
	    	    nameText = nameEditText.getText().toString();
	    	    descriptionText = descriptionEditText.getText().toString();
	    	    //get spinner strings
	    		categoryUpdate = categorySpinner.getSelectedItem().toString();
	    		locationUpdate = locationSpinner.getSelectedItem().toString();
	    		statusUpdate = statusSpinner.getSelectedItem().toString();
	    		dayUpdate = locationSpinner.getSelectedItem().toString();
	    		monthUpdate = locationSpinner.getSelectedItem().toString();
	    	    yearUpdate = locationSpinner.getSelectedItem().toString();
	    	    if(monthUpdate!=null&&monthUpdate.trim().length()!=0&&dayUpdate!=null&&dayUpdate.trim().length()!=0&&yearUpdate!=null&&yearUpdate.trim().length()!=0)
	    	    	dateUpdate = monthUpdate + "/" + dayUpdate + "/" + yearUpdate;
	    	    else
	    	    	dateUpdate ="";
                /*ArrayList<Item> updatedList = new ArrayList<Item>();
                Item it = new Item();
                it.setName("Varun");
                it.setCategory("boytoy");
                it.setStatus("Lost");
                updatedList.add(it);
                it = new Item();
                it.setName("Zubes");
                it.setCategory("girltoy");
                it.setStatus("Lost");
                updatedList.add(it);
                it = new Item();
                it.setName("Pav");
                it.setCategory("boy");
                it.setStatus("Lost");
                updatedList.add(it);
                it = new Item();
                it.setName("Wes");
                it.setCategory("lilfuck");
                it.setStatus("Found");
                updatedList.add(it);
                it = new Item();
                it.setName("Nits");
                it.setCategory("girltoy");
                it.setStatus("Found");
                updatedList.add(it);
                
                */

                DBHelper ft = new DBHelper(SearchItemActivity.this);
	    		ft.open();
	    		ArrayList<Item> updatedList = ft.filterItems(nameText, descriptionText, categoryUpdate, statusUpdate, dateUpdate, null);
	    		ft.close();
                Toast t = Toast.makeText(thisActivity, updatedList.size()+";"+nameText+";"+descriptionText+";"+categoryUpdate+";"+statusUpdate+";"+dateUpdate, Toast.LENGTH_SHORT);
                t.show();	    		
	    		
	    		for(Item i: updatedList) {
	    			name = i.getName();
	    			description = i.getDescription();
	    			category = i.getCategory();
	    			status = i.getStatus();
	    			date = i.getDate();
	    			userid = i.getUserID();
	    			results.setText(results.getText()+"* "+name+": "+category+","+status+","+"\n");
	    		}
	    		
	    	}	 
	    	
	    });
	    

		
		
	}//end onCreate
	


	public void onDestroy()
	{
		super.onDestroy();
		//LoginActivity.database.closeDB();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_search_item, menu);
		return true;
	}

}
