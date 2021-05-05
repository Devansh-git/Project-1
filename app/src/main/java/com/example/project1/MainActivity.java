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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
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

public class MainActivity<var> extends AppCompatActivity {

    RecyclerView TaskRecycleView ;

    private ArrayList<DisplayTask> allTasks;
    private ArrayList<DisplayTask> nonCompletedTasks;
    private Switch aSwitch;

    private TaskAdapter taskAdapter;
    private RecyclerView.LayoutManager layoutManager;

    TaskManager taskManager;
    RelativeLayout relativeLayout;

    private double incomplete;
    private double all;

    ProgressBar pbUi;
    TextView pbText , Messsage;

    @Override
    protected void onStart() {
        super.onStart();


        incomplete=0;
        all=0;


        setupDatabase();
        startDisplay();

        pbUi = (ProgressBar)findViewById(R.id.pbUI);
        pbText = (TextView)findViewById(R.id.pbText);
        Messsage = (TextView)findViewById(R.id.tvMessage);


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

        double ratio = (all - incomplete)/all  ;

         int displayPer = (int) (ratio*100);

        pbUi.setProgress(displayPer);
        pbText.setText( displayPer + " %");

       if(displayPer<=25)
       {
           Messsage.setText("Completed "+ displayPer + "% of your tasks.\n"+ "Make sure you complete the tasks.");
       }
       else if(displayPer<=50)
       {
           Messsage.setText("Completed "+ displayPer + "% of your tasks.\n"+" Keep up the work.");
       }
       else if(displayPer<=75)
       {
           Messsage.setText("Completed "+ displayPer + "% of your tasks.\n"+ "Almost there.");
       }
        else if(displayPer<100)
       {
           Messsage.setText("Completed "+ displayPer + "% of your tasks.\n" + "You can relax a bit.");
       }
        else if(displayPer==100)
       {
           Messsage.setText("All Tasks Done !!");
       }

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

        all = allTasks.size();
        incomplete = nonCompletedTasks.size();






    }

    public void setAll()
    {
        TaskRecycleView = (RecyclerView)findViewById(R.id.displayTaskRecycle);
        setupDatabase();

        layoutManager = new LinearLayoutManager(this);
        taskAdapter = new TaskAdapter(allTasks,MainActivity.this);
        TaskRecycleView.setLayoutManager(layoutManager);
        TaskRecycleView.setAdapter(taskAdapter);

        taskAdapter.notifyDataSetChanged();
    }



    public void startDisplay()
    {
        setupDatabase();
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

    public void openSettings(View view) {

    }
}