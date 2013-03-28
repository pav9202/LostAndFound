package com.example.lostandfound.util;

public class Item {
	protected String name;
	protected String description;
	protected String userID;
	protected String category;
	protected String date;
	protected String status;
	public String getName(){
		return name;
	}
	public String getDescripion(){
		return description;
	}
	public void setName(String a){
		name = a;
	}
	public String getCategory(){
		return category;
	}
	public void setCategory(String a){
		category = a;
	}
	public String getStatus(){
		return status;
	}
	public void setStatus(String a){
		status = a;
	}
	public String getDate(){
		return date;
	}
	public void setDate(String a){
		date = a;
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
