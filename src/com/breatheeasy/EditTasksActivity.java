package com.breatheeasy;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EditTasksActivity extends Activity{
	
	protected DatabaseHandler db;
	protected ArrayAdapter<String> adapter;
	protected Task[] tasks;
	protected String[] task_texts;
	protected ListView tasksList;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		setContentView(R.layout.activity_edit_tasks);
		
		super.onCreate(savedInstanceState);
		db = new DatabaseHandler(this);
		
		update_list_view();
		
	}

	
	public void deleteItem(View v){
		int position = tasksList.getPositionForView(v);
		
		Log.w("EditTasksActivity", "got click on pos: " + position);
		Task t = tasks[position];
		db.deleteTask(t);
		update_list_view();
		tasksList.invalidateViews();
	}
	
	private void update_list_view(){
		//Task[] tasks = new Tasks[db.getTasksCount()];
		tasks = db.getAllTasks().toArray(new Task[0]);
				
		task_texts = new String[tasks.length];
		
		int i = 0;
		for(Task t : tasks){
			task_texts[i] = t.getText();
			i++;
		}
		

		adapter = new ArrayAdapter<String>(
					this,
					R.layout.task_list_item,
					R.id.view_task_list_item,
					task_texts
				);

		tasksList = (ListView) findViewById(R.id.edit_tasks_list);
		tasksList.setAdapter(adapter);
				
	}

}
