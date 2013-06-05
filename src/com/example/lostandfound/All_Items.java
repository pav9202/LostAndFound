package com.example.lostandfound;

import java.util.ArrayList;

import com.example.lostandfound.util.DBHelper;
import com.example.lostandfound.util.Item;
import com.example.lostandfound.util.User;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class All_Items extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all__items);
		
		TextView results = (TextView)findViewById(R.id.listOfAllItems);
		results.setText("");
		DBHelper ft = new DBHelper(this);
		ft.open();
		ArrayList<Item> updatedList = ft.getAllItems();
		ft.close();
		
		
		for(Item i: updatedList) {
			String name = i.getName();
			String description = i.getDescription();
			String category = i.getCategory();
			String status = i.getStatus();
			String date = i.getDate();
			String userid = i.getUserID();
			results.setText(results.getText()+"* "+name+": "+category+","+status+","+"\n");
		}
		results = (TextView)findViewById(R.id.listOfAllItems);
		results.setText(results.getText()+"--------------\n");
		ft = new DBHelper(this);
		ft.open();
		ArrayList<User> updatedList2 = ft.getAllUsers();
		ft.close();
		
		
		for(User i: updatedList2) {
			String name = i.getUsername();
			String status = i.isLocked+"";
			results.setText(results.getText()+"* "+name+": "+i.getPassword()+"|"+status+"\n");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_all__items, menu);
		return true;
	}

}
