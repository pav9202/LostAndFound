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

public class ItemDatabaseFoundUtility {
	/*
	 * How to use:
	 * 
	 * ----------------------------Write to text file----------------------------------
	 * userDataBase = new UserDatabaseUtility
	 * String output = "itemName,itemDescription,itemID,itemCategory\n"
	 * userDatabase.writeToUserDB(output, "loginInformation/testFile.txt");
	 * 
	 *--------------------------------Read text file--------------------------------------
	 * String fileContents = userDatabase.readFromUserDB("loginInformation/testFile.txt");
	 * */
	String FILENAME = "itemsFoundData.txt";
	/**
	 * This function writes a text file to the SD card depending on the given path
	 * 
	 * @param String output String that needs to be put into file
	 * @param String path Path that the text file is located in
	 * @return boolean True if file was written, False if not
	 * @throws IOException 
	 * */
	public boolean writeToItemDBFound() throws IOException{
		  
		  OutputStreamWriter out = null;
		  String fullPath = Environment.getExternalStorageDirectory().getPath();//Create file path
		  /* Gets the Android external storage directory. 
		   * This directory may not currently be accessible if it has been mounted 
		   * by the user on their computer, has been removed from the device, or 
		   * some other problem has happened. */
		  File file = new File(fullPath + "/" + FILENAME); //Create a new file with that path
		  
		  if (!file.exists())
		  {
			  file.createNewFile();
			  return true;
		  }

		  ArrayList<Item> a = LoginActivity.itemsFound;//fix this
		  for(Item acc:a)
		  {
			  try
			  {
				  out = new OutputStreamWriter(new FileOutputStream(file));
				  out.write(acc.getName()+","+acc.getDescripion()+","+acc.getUserID()+","+acc.getCategory()+"\n");   
			  }
			  catch(Exception e)
			  {
				  e.printStackTrace(); 
				  return false;
			  }
		  }
		  	
		  try 
		  {
			out.flush();
		  } 
		  catch (IOException e1) 
		  {
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
	public ArrayList<Item> readFromItemDBFound() throws IOException
	{ 
		  String fullPath = Environment.getExternalStorageDirectory().getPath() + "/" + FILENAME; //Find file within path
		  File file = new File(fullPath); 
		  file.createNewFile();
		  
		  if(!file.exists()){
			  String completePath = Environment.getExternalStorageDirectory().getPath() + "/" + FILENAME; //Create file path
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
		  reader.close();
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
		 }
		}
