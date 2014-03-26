package com.breatheeasy;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initialize_tasks();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void showRandomItem(View view){
		TextView t = (TextView) findViewById(R.id.visible_task);
		DatabaseHandler db = new DatabaseHandler(this);
		int count = db.getTasksCount();
		
		Random rand = new Random();
		int randomId = rand.nextInt((count - 1) + 1) + 1;
		Log.i("Main activity","count: " + count);
		Log.i("Main activity","Got random: " + randomId);
		Task task = db.getTask(randomId); 
		t.setText(task.getText());
	}
	
	// used for development
	private void initialize_tasks(){
		DatabaseHandler db = new DatabaseHandler(this);
		db.deleteAllTasks();
		db.addTask(new Task("Say hello"));
		db.addTask(new Task("Lorem ipsum dolor"));
		db.addTask(new Task("Little Chicken rocks"));
	}

}
