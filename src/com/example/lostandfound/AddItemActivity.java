           
package com.example.lostandfound;
import java.util.LinkedList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.lostandfound.util.Item;

public class AddItemActivity extends Activity {

	private Spinner itemDropDown;
	private Button confirm;
	private Button cancel;
	private Button foundButton;
	private Button searchButton;
	private OnClickListener confirmListener;
	private OnClickListener cancelListener;
	private OnClickListener foundOptionListener;
	private OnClickListener searchOptionListener;
	private EditText descriptionField;
	private EditText nameField;
	private String itemCategory;
	private String itemDescription;
	private String itemName;
	//private final String SAVE_LOCATION = "";
	private LinkedList<String> items;
	
	private Activity thisActivity;
	EditText name;
	DatePicker date;
	Spinner category;
	Spinner status;
	/**
	 * Initialization of Listeners and Views
	 * 
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_item);		
		thisActivity = this;
		
		name = (EditText)findViewById(R.id.nameOfItem);
		date = (DatePicker)findViewById(R.id.dateOfItem);
		category = (Spinner)findViewById(R.id.spinner1);
		status = (Spinner)findViewById(R.id.spinner1);
		Button submit = (Button)findViewById(R.id.submitItem);
		Button cancel = (Button)findViewById(R.id.cancel);

		submit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Item newone = new Item();
				newone.setName(name.getText().toString());
				newone.setDate(date.getMonth()+"/"+date.getDayOfMonth()+"/"+date.getYear());
				newone.setCategory(String.valueOf(category.getSelectedItem()));
				newone.setStatus(String.valueOf(status.getSelectedItem()));
				newone.setUserID(LoginActivity.curAcc.getID());
				if(newone.getStatus().equals("Lost"))
					LoginActivity.itemsLost.add(newone);
				else
					LoginActivity.itemsFound.add(newone);
				Intent intent = new Intent(thisActivity, HomeActivity.class);
				startActivity(intent);
			}
			
		});
		cancel.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent(thisActivity, HomeActivity.class);
				startActivity(intent);
			}
			
		});
		
		
		
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