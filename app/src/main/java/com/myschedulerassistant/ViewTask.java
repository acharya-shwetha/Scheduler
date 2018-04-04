package com.myschedulerassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ViewTask extends AppCompatActivity {
    private long taskID;
    private TextView selectedPlace;
    private TextView purpose;
    private TextView duration;
    private TextView dateAndTime;
    private DBHelper database;
    private Task selectedTask;
    private Button doneButton;
    private Button editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);
        database = DBHelper.getInstance(this);
        database.open();
        Intent i = getIntent();
        //get task id from intent
        taskID = i.getLongExtra("Task ID",0);
        selectedTask = database.getTaskById(taskID);
        selectedPlace = (TextView) findViewById(R.id.view_selected_place);
        selectedPlace.setText(selectedTask.getTaskPlace());
        purpose = (TextView) findViewById(R.id.view_purpose);
        purpose.setText(selectedTask.getTaskName());
        duration = (TextView) findViewById(R.id.View_duration);
        duration.setText(selectedTask.getDuration().toString());
        dateAndTime = (TextView) findViewById(R.id.view_dateTime);
        dateAndTime.setText(selectedTask.getDateTime().toString());
        doneButton = (Button) findViewById(R.id.doneButton);
        doneButton.setOnClickListener(mOnClickListener);
        editButton = (Button) findViewById(R.id.EditButton) ;
        editButton.setOnClickListener(mOnClickListener);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.doneButton:
                    database.deleteTask(taskID);
                    finish();
                    break;
                case R.id.EditButton:
                    Intent editIntent = new Intent(ViewTask.this,EditLocation.class);
                    editIntent.putExtra("EDIT_TASK",taskID);
                    startActivity(editIntent);
                    finish();
                    break;
            }
        }
    };
}
