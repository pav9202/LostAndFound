package com.example.lostandfound.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;

import com.example.lostandfound.LoginActivity;

import android.os.Environment;

public class UserDatabaseUtility {
	/*
	 * How to use:
	 * 
	 * ----------------------------Write to text file----------------------------------
	 * userDataBase = new UserDatabaseUtility
	 * String output = "Mark@x.com,password1,0001,Admin\n
	 * 					Jacob,password2,0002,User\n
	 * 					Varun,password3,0003,User\n"
	 * userDatabase.writeToUserDB(output, "loginInformation/testFile.txt");
	 * 
	 *--------------------------------Read text file--------------------------------------
	 * String fileContents = userDatabase.readFromUserDB("loginInformation/testFile.txt");
	 * */
	/**
	 * This function writes a text file to the SD card depending on the given path
	 * 
	 * @param String output String that needs to be put into file
	 * @param String path Path that the text file is located in
	 * @return boolean True if file was written, False if not
	 * @throws IOException 
	 * */
	public boolean writeToUserDB() throws IOException{
		  
		  OutputStreamWriter out = null;
		  String fullPath = Environment.getExternalStorageDirectory().getPath();//Create file path
		  /* Gets the Android external storage directory. 
		   * This directory may not currently be accessible if it has been mounted 
		   * by the user on their computer, has been removed from the device, or 
		   * some other problem has happened. */
		  File file = new File(fullPath + "/" + "userData.txt"); //Create a new file with that path
		  if (!file.exists()){
			  file.createNewFile();
			  return true;
		  }
		  ArrayList<Account> a = LoginActivity.accounts;
		  for(Account acc:a){
			  try{
				  out = new OutputStreamWriter(new FileOutputStream(file));
				  out.write(acc.getUsername()+","+acc.getPassword()+","+acc.ID+","+acc.getAccountType()+"\n"); 
				  
			  }catch(Exception e){
				  e.printStackTrace(); 
				  return false;
			  }
		  }
		  try {
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //forced write to sd card
		  try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return true; 
		 }
/**
 * This function reads a text file from the SD card given the file's name
 * 
 * @param String fileName Name of the file that needs to be read
 * @return String String representation of the text file
 * @throws IOException 
 * */
	public ArrayList<Account> readFromUserDB() throws IOException{ 
		  String fullPath = Environment.getExternalStorageDirectory().getPath();// + "/" + "userData.txt"; //Find file within path
		  File file = new File(fullPath + "/" + "userData.txt"); 
		  
		  if(!file.exists()){ //Create file if it doesn't exist
			  //String completePath = Environment.getExternalStorageDirectory() + "/" + "userData.txt"; //Create file path
			  File newFile = new File(fullPath + "/" + "userData.txt");
			  newFile.createNewFile();
			  return new ArrayList<Account>();
		  }
		  
		  BufferedReader reader = null;
		  StringBuilder builder = new StringBuilder(); //string representation of the file
		  try{
		   reader = new BufferedReader(new FileReader(file));
		   String line; 
		   while((line = reader.readLine()) != null){
		    builder.append(line); 
		   }
		  }catch(Exception e){
		   e.printStackTrace(); 
		  }
		  //return builder.toString(); //we need to parse through this and put this into a HashTable
		  ArrayList<Account> accounts = new ArrayList<Account>();
		  String string = builder.toString();
		  String[] s = string.split("\n");
		  for(String e:s){
			  String[] t = e.split(",");
			  Account newAcc = null;
			  if(t[3].equals("Admin")){
				  newAcc = new Admin(t[2]);
				  newAcc.setUsername(t[0]);
				  newAcc.setPassword(t[1]);
				  newAcc.setType(t[3]);
			  }
			  if(t[3].equals("User")){
				  newAcc = new User(t[2]);
				  newAcc.setUsername(t[0]);
				  newAcc.setPassword(t[1]);
				  newAcc.setType(t[3]);
			  }
			  accounts.add(newAcc);
		  }
		  return accounts;
		  
		  /*String s = builder.toString();
		  
		  String[] token = s.split("\n");*/
		 }
		}
