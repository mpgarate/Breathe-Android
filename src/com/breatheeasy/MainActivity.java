package com.breatheeasy;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

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
		
		Task task = Task.getRandomTask(new DatabaseHandler(this));

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
