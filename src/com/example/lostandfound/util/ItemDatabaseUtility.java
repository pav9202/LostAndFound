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

public class ItemDatabaseUtility {
	/*
	 * How to use:
	 * 
	 * ----------------------------Write to text file----------------------------------
	 * userDataBase = new UserDatabaseUtility
	 * String output = "something,it is something,0001,category\n"
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
		  File file = new File(fullPath + "/" + "itemData.txt"); //Create a new file with that path
		  if (!file.exists()){
			  file.createNewFile();
			  return true;
		  }
		  file.createNewFile();
		  ArrayList<Item> a = LoginActivity.items;
		  for(Item acc:a){
			  try{
				  out = new OutputStreamWriter(new FileOutputStream(file));
				  out.write(acc.getName()+","+acc.getDescripion()+","+acc.getUserID()+","+acc.getCategory()+"\n"); 
				  
			  }catch(Exception e){
				  e.printStackTrace(); 
				  return false;
			  }
		  	}
		  try {
			out.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
	public ArrayList<Item> readFromUserDB() throws IOException{ 
		  String fullPath = Environment.getExternalStorageDirectory().getPath() + "/" + "itemData.txt"; //Find file within path
		  File file = new File(fullPath); 
		  file.createNewFile();
		  
		  if(!file.exists()){
			  String completePath = Environment.getExternalStorageDirectory().getPath() + "/" + "itemData.txt"; //Create file path
			  File newfile = new File(completePath);
			  newfile.createNewFile();
			  return new ArrayList<Item>();
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
		  ArrayList<Item> items = new ArrayList<Item>();
		  String string = builder.toString();
		  String[] s = string.split("\n");
		  for(String e:s){
			  String[] t = e.split(",");
			  Item item = new Item();
			  item.setName(t[0]);
			  item.setDescription(t[1]);
			  item.setUserID(t[2]);
			  item.setCategory(t[3]);
			  items.add(item);
		  }
		  return items;
		  
		  /*String s = builder.toString();
		  
		  String[] token = s.split("\n");*/
		 }
		}
