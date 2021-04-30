package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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

    private ArrayList<DisplayTask> displayTasks;
    private TaskAdapter taskAdapter;
    private RecyclerView.LayoutManager layoutManager;

    TaskManager taskManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayTasks = new ArrayList<>();

        try{
            taskManager = new TaskManager(this);
        }catch (Exception e)
        {
            Log.i("Error : ",e.getMessage());
        }

        TaskModel task = new TaskModel(1,"Fix","Door","12/12/2021","14/12/2021","12:00",true);

        try{
            taskManager.addOne(task);
            taskManager.addOne(task);
            Toast.makeText(MainActivity.this,"added",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.i("Error : ",e.getMessage());
        }

        try{
            TaskModel tm = taskManager.getOne();
            displayTasks.add(new DisplayTask(tm.id,tm.task,tm.getDate(),R.drawable.cart,tm.getCompleted()));
            Toast.makeText(MainActivity.this,tm.getTask(),Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Log.i("Error : ",e.getMessage());
        }


        TaskRecycleView = (RecyclerView)findViewById(R.id.displayTaskRecycle);

        layoutManager = new LinearLayoutManager(this);



        taskAdapter = new TaskAdapter(displayTasks);


        TaskRecycleView.setLayoutManager(layoutManager);
        TaskRecycleView.setAdapter(taskAdapter);





    }






    public void InsertTask(View view)
    {
        Intent intent = new Intent(this,InsertTask.class);
        startActivity(intent);
    }
}