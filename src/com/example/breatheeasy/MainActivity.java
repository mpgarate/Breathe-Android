package com.example.breatheeasy;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
		long task_id = db.addTask(new Task("Say hello"));
		Task task = db.getTask((int)task_id);
		t.setText(task.getText());
	}

}
