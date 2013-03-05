package com.example.lostandfound.util;

import com.example.lostandfound.LoginActivity;

public class Admin extends User {
	
	public Admin(){
		this.ID = LoginActivity.accounts.size()+1 +"";
		this.isLocked=false;
	}
	public Admin(String id){
		this.ID = id;
		this.isLocked=false;
	}
	@Override
	public void setType(){
		this.type = "Admin";
	}
	@Override
	public String getAccountType(){
		return "Admin";
	}
	
	public void lockUser(String username){
		
		this.status = "Locked";
	}
	
}
