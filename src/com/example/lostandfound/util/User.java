package com.example.lostandfound.util;

import com.example.lostandfound.LoginActivity;

public class User extends Account {
	String status = "Unlocked";
	/*
	public User(Context){
		this.ID = LoginActivity.accounts.size()+1 +"";
		this.tries = 0;
		this.isLocked=false;
	}*/
	/*
	public User(String username, String password, String type, String id)
	{
		this();
		this.username = username;
		this.password = password;
		this.type = type;
		this.ID = id;
		
	}*/
	
	
	public User(){
		//this.ID = id;
		this.tries=0;
		this.isLocked=false;
	}

	void setType(){
		this.type = "User";
	}
	
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public String getUsername(){
		return this.username;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getPassword(){
		return this.password;
	}

	@Override
	public String getAccountType() {
		// TODO Auto-generated method stub
		return this.type;
	}


	@Override
	public void setType(String a) {
		// TODO Auto-generated method stub
		this.type= a;
	}

	public void lock(){
		this.isLocked = true;
	}
	public void unlock(){
		this.isLocked = false;
	}
	@Override
	public void addTry() {
		// TODO Auto-generated method stub
		this.tries++;
		if(this.tries>3){
			lock();
		}
	}
	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return this.ID;
	}
	public String toString(){
		return this.username+":"+this.password;
	}
	public void setID(String i) {
		// TODO Auto-generated method stub
		this.ID =i;
	}
}
