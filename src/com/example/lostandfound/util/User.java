package com.example.lostandfound.util;

import com.example.lostandfound.LoginActivity;

public class User extends Account {
	String status = "Unlocked";
	
	public User(){
		this.ID = LoginActivity.accounts.size()+1 +"";
		this.tries = 0;
		this.isLocked=false;
	}
	public User(String id){
		this.ID = id;
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
		return "User";
	}


	@Override
	public void setType(String a) {
		// TODO Auto-generated method stub
		this.type= a;
	}

	public void lock(){
		this.isLocked = true;
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
}
