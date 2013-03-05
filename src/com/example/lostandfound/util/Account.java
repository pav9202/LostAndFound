package com.example.lostandfound.util;

public abstract class Account {

	protected String username;
	protected String password;
	protected String type;
	protected String ID;
	protected int tries;
	public boolean isLocked;
	
	public abstract String getAccountType();
	public abstract String getUsername();
	public abstract String getPassword();
	public abstract String getID();
	public abstract void setUsername(String a);
	public abstract void setPassword(String a);
	public abstract void setType(String a);
	public abstract void lock();
	public abstract void addTry();
	
}
