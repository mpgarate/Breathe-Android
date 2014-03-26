package com.breatheeasy;

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
}
