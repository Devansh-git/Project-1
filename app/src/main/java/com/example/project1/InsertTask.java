package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.project1.Adapters.SpinnerAdapter;
import com.example.project1.Database.TaskManager;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class InsertTask extends AppCompatActivity {

    private ArrayList<TaskType> taskTypes;
    private SpinnerAdapter Adapter;

    Spinner spntaskType;
    EditText Task,comDate,notDate,notTime;

    TaskManager taskManager;


    String taskType,task,CompletionDate,NotifyDate,NotifyTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_task);

        setUpTaskType();

        try{
            taskManager = new TaskManager(this);
        }
        catch (Exception e)
        {
            Log.e("Error : ", e.getMessage() );
        }




        spntaskType = (Spinner) findViewById(R.id.spnTaskType);

        Adapter = new SpinnerAdapter(this,taskTypes);
        spntaskType.setAdapter(Adapter);

        spntaskType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TaskType clickedItem = (TaskType) parent.getItemAtPosition(position);
                taskType=clickedItem.getTaskType();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });







    }




    private void setUpTaskType()
    {
        taskTypes = new ArrayList<>();
        taskTypes.add(new TaskType("Do",R.drawable.checked));
        taskTypes.add(new TaskType("Fix",R.drawable.wrench));
        taskTypes.add(new TaskType("Buy",R.drawable.cart));
    }


    public void add(View view)
    {

        Task = (EditText)findViewById(R.id.etTask);
        task = Task.getText().toString();
        comDate = (EditText)findViewById(R.id.etCompletionDate);
        CompletionDate = comDate.getText().toString();
        notDate = (EditText)findViewById(R.id.etNotifyDate);
        NotifyDate = notDate.getText().toString();
        notTime = (EditText)findViewById(R.id.etNotifyTime);
        NotifyTime = notDate.getText().toString();

        TaskModel insertTask = new TaskModel();
        insertTask.setTaskType(taskType);
        insertTask.setTask(task);
        insertTask.setDate(CompletionDate);
        insertTask.setNotifyDate(NotifyDate);
        insertTask.setNotifyTime(NotifyTime);
        insertTask.setCompleted(false);

        try {
            taskManager.addOne(insertTask);
        } catch (Exception e) {
            Log.e("Failed to add because - ", e.getMessage() );
        }

    }


    public void goBack(View view)
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}