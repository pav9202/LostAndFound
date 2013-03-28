package com.example.lostandfound.util;

import java.util.ArrayList;

import android.util.Log;
/**
 * This class is used to search for items
 * */
public class SearchUtility {

	/**Search item by name
	 * 
	 * @param String name - the name that is being searched for
	 * @return Item - Item matching name (not case sensitive)
	 * */
	public Item searchByName(String name, ArrayList<Item> arraylist){
		for(int i = 0; i < arraylist.size(); i++){
			if(name.equalsIgnoreCase(arraylist.get(i).getName())){
				return arraylist.get(i);
			} else {
				//Change the below statement to some error statement (bring up a dialog box on front end)
				Log.e("The item does not exist", "The item does not exist");
			}
		}
		return null;
	}
	
	/**Search item by category
	 * 
	 * @param String name - the name that is being searched for
	 * @return Item - Item matching name (not case sensitive)
	 * */
	public Item searchByCategory(String category,ArrayList<Item> arraylist){
		for(int i = 0; i < arraylist.size(); i++){
			if(category.equalsIgnoreCase(arraylist.get(i).getCategory())){
				return arraylist.get(i);
			} else {
				//Change the below statement to some error statement (bring up a dialog box on front end)
				Log.e("The item does not exist", "The item does not exist");
			}
		}
		return null;
	}
	
	/**Filter item by category
	 * 
	 * @param String category - the category that is being filtered by
	 * @return ArrayList<Item> - arraylist of items fitting filter requirements for category
	 * */
	public ArrayList<Item> filterByCategory(String category, ArrayList<Item> arraylist){
		ArrayList<Item> ret = new ArrayList<Item>();
		for(int i = 0; i < arraylist.size(); i++){
			if(category.equalsIgnoreCase(arraylist.get(i).getCategory())){
				ret.add(arraylist.get(i)); //each element is an item
			} else {
				//change the below statement
				continue; //basically I want to write "do nothing" here (is this right)
				//Log.e("The item does not exist", "The item does not exist");
			}
		}
		return ret;
	}
	/**Filter item by date
	 * 
	 * @param String date - the date that is being filtered by
	 * @return ArrayList<Item> - arraylist of items fitting filter requirements for category
	 * */
	public ArrayList<Item> filterByDate(String mmddyyyy, ArrayList<Item> arraylist){ //date is MM/DD/YYYY format
		ArrayList<Item> ret = new ArrayList<Item>();
		String[] tokenizedDate = mmddyyyy.split("/"); //tokenizedDate = {MM, DD, YYYY}
		String dateAsNum = tokenizedDate[2] + tokenizedDate[0] + tokenizedDate[1]; //YYYYMMDD
		int givenDate = Integer.parseInt(dateAsNum); //should convert string to int
		for(int i = 0; i < arraylist.size(); i++)
		{
			String[] tk = ((arraylist.get(i).getDate()).split("/"));
			String dte = tk[2] + tk[0] + tk[1]; //Do this in 1 line of code if possible?
			int itemInArrDate = Integer.parseInt(dte);
			if(givenDate < itemInArrDate)
			{
				ret.add(arraylist.get(i));
			} 
		}
		return ret;
		
	}
	/**Filter items by type/status
	 * 
	 * @param type - the type of item to be searched for (found or lost)
	 * @return ret - arraylist of items that fit the filter for status
	 * */
	public ArrayList<Item> filterByStatus(String status, ArrayList<Item> arraylist){ 
		ArrayList<Item> ret = new ArrayList<Item>();
		for(int i = 0; i < arraylist.size(); i++){
			if(status.equalsIgnoreCase(arraylist.get(i).getStatus())){
				ret.add(arraylist.get(i));
			}
		}
		return ret;
	}
	
}
