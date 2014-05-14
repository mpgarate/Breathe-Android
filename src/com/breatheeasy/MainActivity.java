package com.breatheeasy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {


	private Task currentTask;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initialize_tasks();
		showRandomItem();
	}
	
	private void startEditTasks(){
		Intent intent = new Intent(this, EditTasksActivity.class);
		startActivity(intent);
	}
	
	public void showRandomItem(View view){
		showRandomItem();
	}
	
	public void showRandomItem(){
		TextView t = (TextView) findViewById(R.id.visible_task);
		
		// Attempt to set currentTask
		if (currentTask == null){
			currentTask = Task.getRandomTask(new DatabaseHandler(this));
		}
		else{
			currentTask = Task.getRandomTask(new DatabaseHandler(this), currentTask.getID());
		}
		
		// Handle case where none found
		if (currentTask == null){
			t.setText("Add some items!");
		}
		else{
			t.setText(currentTask.getText());
		}
	}
	
	// used for development
	private void initialize_tasks(){
		DatabaseHandler db = new DatabaseHandler(this);
		db.deleteAllTasks();
		db.addTask(new Task("Say hello"));
		db.addTask(new Task("Read a book"));
		db.addTask(new Task("Listen to music"));
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
		case R.id.action_edit_tasks:
			startEditTasks();
			return true;
		case R.id.action_settings:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
}
