package com.example.lostandfound.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

public class Item {
	protected String name;
	protected String description;
	protected String userID;
	protected String category;
	protected String date;
	protected String id;
	protected String status;
	protected Uri pic;
	
	public Item(){
		
	}
	public Item(String n, String d, String uid, String c)
	{
		name = n;
		description = d;
		userID = uid;
		category = c;
	}
	
	public Uri getPic(){
		return pic;
	}
	public Bitmap getBitmap(Context c){
		if(pic!= null){
			InputStream stream = null;
			try {
				stream = c.getContentResolver().openInputStream(pic);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Bitmap bitmap = BitmapFactory.decodeStream(stream);
			try {
				stream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return bitmap;
		}
		return null;
	}
	public void setPic(Uri n){
		pic = n;
	}
	public String getName(){
		return name;
	}
	public String getDescription(){
		return description;
	}
	public void setName(String a){
		name = a;
	}
	public String getDate(){
		return date;
	}
	public void setDate(String a){
		date = a;
	}
	public String getStatus(){
		return status;
	}
	public void setStatus(String a){
		status = a;
	}
	public String getCategory(){
		return category;
	}
	public void setCategory(String a){
		category = a;
	}

	public void setDescription(String a){
		description = a;
	}
	public String getUserID(){
		return userID;
	}
	public void setUserID(String a){
		userID = a;
	}
	public String toString(){
		return this.name;
	}

}
