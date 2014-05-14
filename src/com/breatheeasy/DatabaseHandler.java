package com.breatheeasy;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
	protected static final int DATABASE_VERSION = 1;
	protected static final String DATABASE_NAME = "tasksManager";
	protected static final String TABLE_TASKS = "tasks";

	protected static final String KEY_ID = "id";
	protected static final String KEY_TEXT = "text";

	public DatabaseHandler(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_TASKS_TABLE = "CREATE TABLE " + TABLE_TASKS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_TEXT + " TEXT"
				+ ");";
		db.execSQL(CREATE_TASKS_TABLE);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
		
		onCreate(db);
	}
	
	public long addTask(Task task){
		SQLiteDatabase db = this.getWritableDatabase();
		
		
		ContentValues values = new ContentValues();
		values.put(KEY_TEXT, task.getText());
		
		long task_id = db.insert(TABLE_TASKS, null, values);

		return task_id;

	}
	
	public Task getRandomTask(){
		SQLiteDatabase db = this.getReadableDatabase();
		
		String rand_order_lim_1 = " ORDER BY RANDOM() LIMIT 1;";
		
		String selectQuery = "SELECT * FROM " + TABLE_TASKS + rand_order_lim_1;
		
		Cursor c = db.rawQuery(selectQuery, null);
		
		Task t = new Task();
		
		if (c.getCount() > 0){
			c.moveToFirst();

			t.setID(c.getInt(c.getColumnIndex(KEY_ID)));
			t.setText(c.getString(c.getColumnIndex(KEY_TEXT)));
		}
		else{
			t.setText("error finding random task");
			return null;
		}
		
		return t;
	}
	
	public Task getTask(int id){
		SQLiteDatabase db = this.getReadableDatabase();
		
		String selectQuery = "SELECT * FROM " + TABLE_TASKS + " WHERE "
				+ KEY_ID + " = " + String.valueOf(id);
		
		Cursor c = db.rawQuery(selectQuery, null);
		
		Task t = new Task();
		
		if (c.getCount() > 0){
			c.moveToFirst();

			t.setID(c.getInt(c.getColumnIndex(KEY_ID)));
			t.setText(c.getString(c.getColumnIndex(KEY_TEXT)));
		}
		else{
			t.setText("no such task: " + id);
			return null;
		}
		
		
		return t;
	}
	
	public int getTasksCount(){
		String countQuery = "SELECT  * FROM " + TABLE_TASKS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		
		return cursor.getCount();
	}
	
	public int updateTask(Task task){
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(KEY_TEXT, task.getText());

		return db.update(TABLE_TASKS, values, KEY_ID + " = ?", 
				new String[] {String.valueOf(task.getID()) });
	}
	
	public void deleteTask(Task task){
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_TASKS, KEY_ID + " = ?",
				new String[] {String.valueOf(task.getID()) });
		db.close();
	}
	
	public void deleteAllTasks(){
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_TASKS, null, null);
	}
	
	public List<Task> getAllTasks(){
		List<Task> taskList = new ArrayList<Task>();
		
		String selectQuery = "SELECT  * FROM " + TABLE_TASKS;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		if (cursor.moveToFirst()){
			do{
				Task task = new Task();
				task.setID(Integer.parseInt(cursor.getString(0)));
				task.setText(cursor.getString(1));
				
				taskList.add(task);
			} while(cursor.moveToNext());
		}
		
		return taskList;
	}
}
