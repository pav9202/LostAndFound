package com.example.lostandfound;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.example.lostandfound.util.DBHelper;
import com.example.lostandfound.util.Item;


public class HomeActivity extends Activity {
	private Bitmap fp1,fp2,fp3,fp4,fp5,lp1,lp2,lp3,lp4,lp5;
	private byte[] foundPicture1,foundPicture2,foundPicture3,foundPicture4,foundPicture5,lostPicture1,lostPicture2,lostPicture3,lostPicture4,lostPicture5;
	private ImageView fpi1,fpi2,fpi3,fpi4,fpi5,lpi1,lpi2,lpi3,lpi4,lpi5;
	Activity thisActivity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		thisActivity = this;
		MediaPlayer mp = MediaPlayer.create(thisActivity, R.raw.piano);
		mp.start();



		//setUpPictures();

		Button addItem = (Button)findViewById(R.id.additemfrommain);
		addItem.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) 
			{
				Intent in = new Intent(thisActivity,AddItemActivity.class);
				startActivity(in);
			}
		});

		Button filterItem = (Button)findViewById(R.id.filteritemfrommain);
		filterItem.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) 
			{
				Intent in = new Intent(thisActivity,SearchItemActivity.class);
				startActivity(in);
			}
		});

		Button logOut = (Button)findViewById(R.id.logoutfrommain);
		logOut.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) 
			{
				Intent in = new Intent(thisActivity,LoginActivity.class);
				startActivity(in);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void setUpPictures()
	{
		DBHelper wahoo = new DBHelper(thisActivity);
		wahoo.open();
		ArrayList<Item> its = wahoo.getAllItems();
		wahoo.close();
		ArrayList<Item> l = new ArrayList<Item>();
		ArrayList<Item> f = new ArrayList<Item>();

		for(Item i:its){
			if(i.getStatus().equals("Lost"))
				l.add(i);
			if(i.getStatus().equals("Found"))
				f.add(i);
		}
		if(l.size()>=1&&f.size()>=1){
			if(l.size()<5){
				for(int i =0;i<5-l.size();i++){
					l.add(l.get(l.size()-1));
				}
			}
			if(f.size()<5){
				for(int i =0;i<5-f.size();i++){
					f.add(f.get(f.size()-1));
				}
			}

			// * put the ten pictures in here
			

			fp1 = f.get(f.size()-1).getBitmap(thisActivity);
			fp2 = f.get(f.size()-2).getBitmap(thisActivity);
			fp3 = f.get(f.size()-3).getBitmap(thisActivity);
			fp4 = f.get(f.size()-4).getBitmap(thisActivity);
			fp5 = f.get(f.size()-5).getBitmap(thisActivity);
			
			lp1 = l.get(l.size()-1).getBitmap(thisActivity);
			lp2 = l.get(l.size()-2).getBitmap(thisActivity);
			lp3 = l.get(l.size()-3).getBitmap(thisActivity);
			lp4 = l.get(l.size()-4).getBitmap(thisActivity);
			lp5 = l.get(l.size()-5).getBitmap(thisActivity);

			fpi1 = (ImageView)findViewById(R.id.recentlostpicture1);
			fpi2 = (ImageView)findViewById(R.id.recentlostpicture2);
			fpi3 = (ImageView)findViewById(R.id.recentlostpicture3);
			fpi4 = (ImageView)findViewById(R.id.recentlostpicture4);
			fpi5 = (ImageView)findViewById(R.id.recentlostpicture5);

			lpi1 = (ImageView)findViewById(R.id.recentfoundpicture1);
			lpi2 = (ImageView)findViewById(R.id.recentfoundpicture2);
			lpi3 = (ImageView)findViewById(R.id.recentfoundpicture3);
			lpi4 = (ImageView)findViewById(R.id.recentfoundpicture4);
			lpi5 = (ImageView)findViewById(R.id.recentfoundpicture5);

			fpi1.setImageBitmap(fp1);
			fpi2.setImageBitmap(fp2);
			fpi3.setImageBitmap(fp3);
			fpi4.setImageBitmap(fp4);
			fpi5.setImageBitmap(fp5);

			lpi1.setImageBitmap(lp1);
			lpi2.setImageBitmap(lp2);
			lpi3.setImageBitmap(lp3);
			lpi4.setImageBitmap(lp4);
			lpi5.setImageBitmap(lp5);
		}
	}

}
