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
		int count = db.getTasksCount();
		
		Random rand = new Random();
		int randomId = rand.nextInt((count - 1) + 1) + 1;
		Log.i("Main activity","count: " + count);
		Log.i("Main activity","Got random: " + randomId);
		return db.getTask(randomId);
	}
}
