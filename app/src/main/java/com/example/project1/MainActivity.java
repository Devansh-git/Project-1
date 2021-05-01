package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.project1.Adapters.SpinnerAdapter;
import com.example.project1.Adapters.TaskAdapter;
import com.example.project1.Database.TaskManager;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView TaskRecycleView ;

    private ArrayList<DisplayTask> allTasks;
    private ArrayList<DisplayTask> nonCompletedTasks;
    private Switch aSwitch;

    private TaskAdapter taskAdapter;
    private RecyclerView.LayoutManager layoutManager;

    TaskManager taskManager;


    @Override
    protected void onStart() {
        super.onStart();
        setupDatabase();
        startDisplay();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aSwitch = (Switch)findViewById(R.id.switchShowInComplete);

        setupDatabase();
        startDisplay();


        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aSwitch.isChecked()==true)
                {
                    setAll();
                }
                else  if(aSwitch.isChecked()==false)
                {
                    startDisplay();
                }
            }
        });

    }








    public void setupDatabase()
    {
        allTasks = new ArrayList<>();
        nonCompletedTasks= new ArrayList<>();

        try{
            taskManager = new TaskManager(this);
        }catch (Exception e)
        {
            Log.i("Error : ",e.getMessage());
        }




        try{
            List<TaskModel> tasksFromDatabase = taskManager.getAll();
            for(int i=0;i<tasksFromDatabase.size();i++)
            {
                allTasks.add(new DisplayTask(tasksFromDatabase.get(i).getId(),tasksFromDatabase.get(i).getTask(),tasksFromDatabase.get(i).getDate(),tasksFromDatabase.get(i).getTaskType(),tasksFromDatabase.get(i).getCompleted(),tasksFromDatabase.get(i)));
                if(tasksFromDatabase.get(i).getCompleted()==0)
                {
                    nonCompletedTasks.add(new DisplayTask(tasksFromDatabase.get(i).getId(),tasksFromDatabase.get(i).getTask(),tasksFromDatabase.get(i).getDate(),tasksFromDatabase.get(i).getTaskType(),tasksFromDatabase.get(i).getCompleted(),tasksFromDatabase.get(i)));
                }

            }

        } catch (Exception e) {
            Log.i("Error : ",e.getMessage());
        }





    }

    public void setAll()
    {
        TaskRecycleView = (RecyclerView)findViewById(R.id.displayTaskRecycle);

        layoutManager = new LinearLayoutManager(this);
        taskAdapter = new TaskAdapter(allTasks,MainActivity.this);
        TaskRecycleView.setLayoutManager(layoutManager);
        TaskRecycleView.setAdapter(taskAdapter);
    }



    public void startDisplay()
    {

        TaskRecycleView = (RecyclerView)findViewById(R.id.displayTaskRecycle);

        layoutManager = new LinearLayoutManager(this);
        taskAdapter = new TaskAdapter(nonCompletedTasks,MainActivity.this);
        TaskRecycleView.setLayoutManager(layoutManager);
        TaskRecycleView.setAdapter(taskAdapter);
    }




    public void InsertTask(View view)
    {
        Intent intent = new Intent(this,InsertTask.class);
        startActivity(intent);
    }
}