package com.breatheeasy;

import java.util.Random;

import android.util.Log;

public class Task {
	private int id;
	private String text;
	
	public Task(int id, String text){
		this.text = text;
		this.id = id;
	}
	
	public Task(String text){
		this.text = text;
	}
	
	public Task(){}
	
	public String getText(){
		return this.text;
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public int getID(){
		return this.id;
	}
	
	public void setID(int id){
		this.id = id;
	}
	
	public static Task getRandomTask(DatabaseHandler db){
		return getRandomTask(db, -1);
	}
	
	public static Task getRandomTask(DatabaseHandler db, int omitId){
		int foundId = omitId;
		Task t = null;
		while(foundId == omitId){
			t = db.getRandomTask();
			if (t == null) break;
			foundId = t.getID();
		}
		return t;
	}
}
