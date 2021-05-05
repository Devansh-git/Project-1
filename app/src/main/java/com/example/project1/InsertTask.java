package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.project1.Adapters.SpinnerAdapter;
import com.example.project1.Database.TaskManager;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class InsertTask extends AppCompatActivity {

    private ArrayList<TaskType> taskTypes;
    private SpinnerAdapter Adapter;

    Spinner spntaskType;
    EditText Task,notTime;

    TaskManager taskManager;

    private DatePickerDialog datePickerDialog;
    private DatePickerDialog notifyDatePickerDialog;

    private Button comDatePicker;
    private Button notDatePicker;


    String taskType,task,NotifyTime;


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


        initDatePicker();
        initNotifyDatePicker();
        comDatePicker = findViewById(R.id.sltComDate);
        notDatePicker = findViewById(R.id.sltNotDate);

        comDatePicker.setText(getTodaysDate());
        notDatePicker.setText(getTodaysDate());




    }

    private void initNotifyDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month=month+1;
                String date = makeDateString(day,month,year);
                notDatePicker.setText(date);

            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);


        int style = AlertDialog.THEME_HOLO_LIGHT;

        notifyDatePickerDialog = new DatePickerDialog(this,style,dateSetListener,year,month,day);
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day,month,year);

    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month=month+1;
                String date = makeDateString(day,month,year);
                comDatePicker.setText(date);

            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);


        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this,style,dateSetListener,year,month,day);


    }

    private String makeDateString(int day, int month, int year) {
        return day + "/" + month  +"/"+ year;
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

        String completionDate = (String) comDatePicker.getText();
        String notifyDate = (String) notDatePicker.getText();


        notTime = (EditText)findViewById(R.id.etNotifyTime);
        NotifyTime = notTime.getText().toString();

        TaskModel insertTask = new TaskModel();
        insertTask.setTaskType(taskType);
        insertTask.setTask(task);
        insertTask.setDate(completionDate);
        insertTask.setNotifyDate(notifyDate);
        insertTask.setNotifyTime(NotifyTime);
        insertTask.setCompleted(0);

        try {
            taskManager.addOne(insertTask);
        } catch (Exception e) {
            Log.e("Failed to add because - ", e.getMessage() );
        }

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }


    public void goBack(View view)
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    public void  openNotifyDatePicker(View view){
        notifyDatePickerDialog.show();
    }
}