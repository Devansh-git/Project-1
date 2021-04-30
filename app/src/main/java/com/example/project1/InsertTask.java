package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.project1.Adapters.SpinnerAdapter;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class InsertTask extends AppCompatActivity {

    private ArrayList<TaskType> taskTypes;
    private SpinnerAdapter Adapter;

    Spinner spntaskType;
    EditText Task,comDate,notDate,notTime;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_task);

        setUpTaskType();





        spntaskType = (Spinner) findViewById(R.id.spnTaskType);

        Adapter = new SpinnerAdapter(this,taskTypes);
        spntaskType.setAdapter(Adapter);

        spntaskType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TaskType clickedItem = (TaskType) parent.getItemAtPosition(position);
                String ClickedTask = clickedItem.getTaskType();
                Toast.makeText(InsertTask.this,ClickedTask,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Task = (EditText)findViewById(R.id.etTask);
        comDate = (EditText)findViewById(R.id.etCompletionDate);
        notDate = (EditText)findViewById(R.id.etNotifyDate);
        notTime = (EditText)findViewById(R.id.etNotifyTime);

    }




    private void setUpTaskType()
    {
        taskTypes = new ArrayList<>();
        taskTypes.add(new TaskType("Do",R.drawable.checked));
        taskTypes.add(new TaskType("Fix",R.drawable.wrench));
        taskTypes.add(new TaskType("Buy",R.drawable.cart));
    }



    public void goBack(View view)
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}