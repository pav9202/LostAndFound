           
package com.example.lostandfound;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.Scanner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lostandfound.util.Item;

public class AddItemActivity extends Activity {

	private Spinner itemDropDown;
	private Button confirm;
	private Button cancel;
	private OnClickListener confirmListener;
	private OnClickListener cancelListener;
	private EditText descriptionField;
	private EditText nameField;
	private String itemCategory;
	private String itemDescription;
	private String itemName;
	//private final String SAVE_LOCATION = "";
	private LinkedList<String> items;
	
	private Activity thisActivity;
	
	/**
	 * Initialization of Listeners and Views
	 * 
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_item);
		
		thisActivity = this;
		
		initializeListeners();
		
		initializeViews();
	}

	/**
	 * Default on create code.
	 * 
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		Intent intent = getIntent();
		
		getMenuInflater().inflate(R.menu.activity_home, menu);
		return true;
	}
	
	public void onBackPressed(){
		
	}
	
	/**
	 * initialize the Buttons, EditTexts, and the Spinner (Drop down menu)
	 * 
	 */
	private void initializeViews()
	{
		itemDropDown = (Spinner) findViewById(R.id.item_drop_down);
		descriptionField = (EditText) findViewById(R.id.item_description_field);
		nameField = (EditText) findViewById(R.id.item_name_field);
		cancel = (Button) findViewById(R.id.cancel_button);
		confirm = (Button) findViewById(R.id.confirm_button);
		
		confirm.setOnClickListener(confirmListener);
		cancel.setOnClickListener(cancelListener);
	}
	
	/**
	 * initializes the listeners and the actions to be performed upon clicking a button.
	 * 
	 * 
	 */
	private void initializeListeners()
	{
		confirmListener = new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				//saveItem();
				Item newItem = new Item();
				newItem.setCategory(String.valueOf(itemDropDown.getSelectedItem()));
				newItem.setDescription(descriptionField.getText().toString());
				newItem.setName(nameField.getText().toString());
				newItem.setUserID(LoginActivity.curAcc.getID());
				LoginActivity.items.add(newItem);
				Intent in = new Intent(thisActivity,HomeActivity.class);
				startActivity(in);
			}
		};
		cancelListener = new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				//saveItem();
			
				Intent in = new Intent(thisActivity,HomeActivity.class);
				startActivity(in);
			}
		};
	}
	
	/**
	 * Method for saving the item to the current text file.
	 * 
	 */
	/*private void saveItem()
	{
		itemCategory = String.valueOf(itemDropDown.getSelectedItem());
		itemName = nameField.getText().toString();
		itemDescription = descriptionField.getText().toString();
		
		FileOutputStream out = null;
		
		try 
		{
			out = openFileOutput(SAVE_LOCATION, Context.MODE_PRIVATE);
			out.write(itemName.getBytes());
			out.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	
	}*/
	
	/**
	 * Gets the items that were previously put in the device's memory.
	 * 
	 * @return a LinkedList of all the items in this device's memory.
	 */
	/*private LinkedList<String> getItems()
	{
		Scanner scan = null;
		LinkedList<String> items = new LinkedList<String>();
		try
		{
			scan = new Scanner(openFileInput(SAVE_LOCATION));
			
			while(scan.hasNextLine())
				items.add(scan.nextLine());
		}
		catch(Exception e)
		{
			
		}
		
		scan.close();
		return items;
	}
*/
}