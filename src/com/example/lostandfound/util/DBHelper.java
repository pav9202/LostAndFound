package com.example.lostandfound.util;

import java.util.ArrayList;

import com.example.lostandfound.SearchItemActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHelper {

	/*General db stuff*/
	private static final String DATABASE_NAME = "LostAndFoundDB";
	private static final int DATABASE_VERSION = 1;

	/*usertable vars*/
	private static final String DATABASE_USERTABLE = "userTable";
	public static final String KEY_USERNAME = "_username";
	public static final String KEY_PASSWORD = "_password";
	public static final String KEY_TYPE = "_type";
	public static final String KEY_STATE = "_state";
	public static final String KEY_UID = "_uid";

	/*itemtable vars*/
	private static final String DATABASE_ITEMTABLE = "itemTable";
	public static final String KEY_NAME = "_name";
	public static final String KEY_DESCRIPTION = "_description";
	public static final String KEY_CATEGORY = "_category";
	public static final String KEY_STATUS = "_status";
	public static final String KEY_DATE = "_date";
	public static final String KEY_OWNERID = "_iid";
	//public static final String KEY_PICTURE = "_pic"; TODO 


	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;

	private static class DbHelper extends SQLiteOpenHelper{

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		/**
		 * This constructor creates 2 tables, 1 for items and 1 for users
		 * Columns for userTable: | USERNAME | PASSWORD | TYPE (admin/user) | STATE (locked?) | UID
		 * Columns for itemTable: | NAME | DESCRIPTION | CATEGORY | STATUS (lost/found?) | DATE | OWNERID |
		 * */
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE " + DATABASE_USERTABLE + " (" +
					KEY_USERNAME + " TEXT NOT NULL, " +
					KEY_PASSWORD + " TEXT NOT NULL, " +
					KEY_TYPE + " TEXT NOT NULL, " +
					KEY_STATE + " TEXT NOT NULL, " + 
					KEY_UID + " TEXT NOT NULL);"
					);
			db.execSQL("CREATE TABLE " + DATABASE_ITEMTABLE + " (" +
					KEY_NAME + " TEXT NOT NULL, " +
					KEY_DESCRIPTION + " TEXT NOT NULL, " +
					KEY_CATEGORY + " TEXT NOT NULL, " + 
					KEY_STATUS + " TEXT NOT NULL, " +
					KEY_DATE + " TEXT NOT NULL, " + 
					KEY_OWNERID + " TEXT);"
					); //nulls are allowed for ownerid
					// + KEY_PICTURE + " BLOB");"
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_USERTABLE);
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_ITEMTABLE);
			onCreate(db);
		}		
	}

	/**
	 * DBHelper constructor
	 * @param Context of activity*/
	public DBHelper(Context c){
		ourContext = c;
	}

	/**
	 * Opens writable database
	 * */
	public DBHelper open() throws SQLException{
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}

	/**
	 * Closes database
	 * */
	public void close(){
		ourHelper.close();
	}

	/**
	 * Inserts a user into the database
	 * @param User u
	 *  */
	public long insertUser(User u) {
		ContentValues cv = new ContentValues();
		cv.put(KEY_USERNAME, u.getUsername());
		cv.put(KEY_PASSWORD, u.getPassword());
		cv.put(KEY_TYPE, u.getAccountType());
		cv.put(KEY_STATE, u.status);
		cv.put(KEY_UID, u.getID());
		return ourDatabase.insert(DATABASE_USERTABLE, null, cv);
	}

	/**
	 * Inserts an item into the database
	 * @param User u
	 *  */
	public long insertItem(Item i) {
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, i.getName());
		cv.put(KEY_DESCRIPTION, i.getDescription());
		cv.put(KEY_CATEGORY, i.getCategory());
		cv.put(KEY_STATUS, i.getStatus());
		cv.put(KEY_DATE, i.getDate());
		cv.put(KEY_OWNERID, i.getUserID());//might need to be modified
		//cv.put(KEY_PICTURE, i.getImage());
		return ourDatabase.insert(DATABASE_ITEMTABLE, null, cv);
	}
	

	/**
	 * Returns all items in the database as an ArrayList<Item>
	 * */
	public ArrayList<Item> getAllItems(){
		Cursor c = ourDatabase.rawQuery("SELECT * from " + DATABASE_ITEMTABLE, null);
		ArrayList<Item> ret = new ArrayList<Item>();
		int numRows = c.getCount();
		c.moveToFirst(); //Move cursor to first row
		
		//reconstruct items from the database
		for(Integer i = 0; i < numRows; i++){
			Item toAdd = new Item();
			toAdd.setName(c.getString(c.getColumnIndex(KEY_NAME)));
			toAdd.setDescription(c.getString(c.getColumnIndex(KEY_DESCRIPTION)));
			toAdd.setCategory(c.getString(c.getColumnIndex(KEY_CATEGORY)));
			toAdd.setStatus(c.getString(c.getColumnIndex(KEY_STATUS)));
			toAdd.setDate(c.getString(c.getColumnIndex(KEY_DATE)));
			toAdd.setUserID(c.getString(c.getColumnIndex(KEY_OWNERID)));
			
			ret.add(toAdd);
			c.moveToNext(); //Move cursor to next row
		}
		return ret;
	}
	
	/**
	 * Returns all users in the database as an ArrayList<User>
	 * */
	public ArrayList<User> getAllUsers(){
		Cursor c = ourDatabase.rawQuery("SELECT * from " + DATABASE_USERTABLE, null);
		ArrayList<User> ret = new ArrayList<User>();
		int numRows = c.getCount();
		c.moveToFirst(); //Move cursor to first row
		
		//reconstruct items from the database
		for(Integer i = 0; i < numRows; i++){
			User toAdd = new User();
			toAdd.setUsername(c.getString(c.getColumnIndex(KEY_USERNAME)));
			toAdd.setPassword(c.getString(c.getColumnIndex(KEY_PASSWORD)));
			toAdd.setType(c.getString(c.getColumnIndex(KEY_TYPE)));
			toAdd.status = (c.getString(c.getColumnIndex(KEY_STATE)));
			toAdd.ID=c.getString(c.getColumnIndex(KEY_UID));
			ret.add(toAdd);
			c.moveToNext(); //Move cursor to next row
		}
		return ret;
	}
	
	/**
	 * Deletes a user from the database
	 * @param String username
	 * */
	public void deleteUser(String username) throws SQLException{
		ourDatabase.delete(DATABASE_USERTABLE, KEY_USERNAME + "=\"" + username+"\"", null);
	}
	
	/**
	 * Deletes an item from the database
	 * @param String itemname
	 * */
	public void deleteItem(String itemname) throws SQLException{
		ourDatabase.delete(DATABASE_ITEMTABLE, KEY_NAME + "=\"" + itemname+"\"", null);
	}

	//update stuff?
	
	/**
	 * This method searches an item from a table
	 * */
	public ArrayList<Item> searchByItemName(String itemname){
		Cursor c = ourDatabase.rawQuery("SELECT * from " + DATABASE_ITEMTABLE + 
				" WHERE " + KEY_NAME + "=" + itemname , null);
		int numRows = c.getCount();
		c.moveToFirst();
		ArrayList<Item> ret = new ArrayList<Item>();
		for(Integer i = 0; i < numRows; i++){
			Item toAdd = new Item();
			toAdd.setName(c.getString(c.getColumnIndex(KEY_NAME)));
			toAdd.setDescription(c.getString(c.getColumnIndex(KEY_DESCRIPTION)));
			toAdd.setCategory(c.getString(c.getColumnIndex(KEY_CATEGORY)));
			toAdd.setStatus(c.getString(c.getColumnIndex(KEY_STATUS)));
			toAdd.setDate(c.getString(c.getColumnIndex(KEY_DATE)));
			toAdd.setUserID(c.getString(c.getColumnIndex(KEY_OWNERID)));
			c.moveToNext();
			ret.add(toAdd);
		}
		return ret;
	}
	public ArrayList<Item> filterItems(String name, String description, String category, String status, String date, String ownerid){
		boolean isFirst = true;
		ArrayList<String> argz = new ArrayList<String>(); //arraylist of parameters to search by, conver to array later
		String query = "SELECT * from " + DATABASE_ITEMTABLE + " "; 
		
		if(name != null&&name.length()!=0) { //If name arg isn't null
			argz.add(name);
			if(!isFirst) {
				query += "AND " + KEY_NAME + " = ?";
			} else {
				query += "WHERE " + KEY_NAME + " = ?";
				isFirst = false;
			}
		}
		
		if(description != null&&description.length()!=0) { //if description arg isn't null
			argz.add(description);
			if(!isFirst) {
				query += "AND " + KEY_DESCRIPTION + " = ?";
			} else {
				query += "WHERE " + KEY_DESCRIPTION + " = ?";
				isFirst = false;
			}
		}
		
		if(category != null&&category.length()!=0) { //if category arg isn't null
			argz.add(category);
			if(!isFirst) {
				query += "AND " + KEY_CATEGORY + " = ?";
			} else {
				query += "WHERE " + KEY_CATEGORY + " = ?";
				isFirst = false;
			}
		}
		
		if(status != null&&status.length()!=0) { //if status arg isn't null
			argz.add(status);
			if(!isFirst) {
				query += "AND " + KEY_STATUS + " = ?";
			} else {
				query += "WHERE " + KEY_STATUS + " = ?";
				isFirst = false;
			}
		}
		
		if(date != null&&date.length()!=0) { //if date arg isn't null
			argz.add(date);
			if(!isFirst) {
				query += "AND " + KEY_DATE + " = ?";
			} else {
				query += "WHERE " + KEY_DATE + " = ?";
				isFirst = false;
			}
		}
		
		if(ownerid != null&&ownerid.length()!=0) { //if ownerid arg isn't null
			argz.add(ownerid);
			if(!isFirst) {
				query += "AND " + KEY_OWNERID + " = ?";
			} else {
				query += "WHERE " + KEY_OWNERID + " = ?";
				isFirst = false;
			}
		}
		String[] arguments = new String[argz.size()];
		arguments = argz.toArray(arguments);//convert arraylist of arguments to array
		Toast t = Toast.makeText(SearchItemActivity.thisActivity, arguments.length+"  arg", Toast.LENGTH_SHORT);
		t.show();
		
		Cursor c = ourDatabase.rawQuery(query, arguments);
		int numRows = c.getCount();
		c.moveToFirst();
		ArrayList<Item> ret = new ArrayList<Item>();
		for(Integer i = 0; i < numRows; i++){
			Item toAdd = new Item();
			toAdd.setName(c.getString(c.getColumnIndex(KEY_NAME)));
			toAdd.setDescription(c.getString(c.getColumnIndex(KEY_DESCRIPTION)));
			toAdd.setCategory(c.getString(c.getColumnIndex(KEY_CATEGORY)));
			toAdd.setStatus(c.getString(c.getColumnIndex(KEY_STATUS)));
			toAdd.setDate(c.getString(c.getColumnIndex(KEY_DATE)));
			toAdd.id=c.getString(c.getColumnIndex(KEY_OWNERID));

			c.moveToNext();
			ret.add(toAdd);
		}
		return ret;
	}
	
	/**
	 * Deletes all rows in the userTable
	 * */
	public void removeAllUsers(){
		ourDatabase.delete(DATABASE_USERTABLE, null, null);
	}
	
	/**
	 * Deletes all rows in the itemTable
	 * */
	public void removeAllItems(){
		ourDatabase.delete(DATABASE_ITEMTABLE, null, null);
	}
}
