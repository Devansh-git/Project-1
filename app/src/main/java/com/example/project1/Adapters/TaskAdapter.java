package com.example.project1.Adapters;

import android.content.Context;
import android.content.Entity;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project1.Database.TaskManager;
import com.example.project1.DisplayTask;
import com.example.project1.MainActivity;
import com.example.project1.R;
import com.example.project1.TaskModel;
import com.example.project1.TaskType;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{

    private ArrayList<DisplayTask> mdisplayTasks;

    public TaskManager taskManager;
    Context context;

    public static class TaskViewHolder extends RecyclerView.ViewHolder
    {
        public TextView taskType;
        public TextView Task,TaskDate;
        public LinearLayout taskLayout;
        public Button btnCompleted;


        public TaskViewHolder(View taskView){
            super(taskView);
            taskType= taskView.findViewById(R.id.taskType);
            Task = taskView.findViewById(R.id.Task);
            TaskDate = taskView.findViewById(R.id.taskCompletionDate);
            taskLayout = taskView.findViewById(R.id.taskLayout);

            btnCompleted =taskView.findViewById(R.id.btnSetComplete);

        }


    }

    public TaskAdapter(ArrayList<DisplayTask> displayTasks,Context context)
    {
        mdisplayTasks = displayTasks;
        this.context = context;


    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =LayoutInflater.from(parent.getContext()).inflate(R.layout.task_display_layout,parent,false);

        TaskViewHolder tvh = new TaskViewHolder(v);

        return tvh;
    }


    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        DisplayTask dt = mdisplayTasks.get(position);

        holder.taskType.setText(dt.getTaskType());
        holder.Task.setText(dt.getTask());
        holder.TaskDate.setText(dt.getTaskDate());



        if(dt.getCompleted()==true)
        {
            holder.taskLayout.setBackgroundResource(R.drawable.card_complete_layout);
        }
        else
        {
            holder.taskLayout.setBackgroundResource(R.drawable.card_incomplete_layout);
        }

        TaskManager taskManager = new TaskManager(context);

        holder.btnCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                boolean done = taskManager.setCompleted(dt.getTaskModel());

                if(done==true)
                {
                    holder.taskLayout.setBackgroundResource(R.drawable.card_complete_layout);
                }
                else
                {
                    holder.taskLayout.setBackgroundResource(R.drawable.card_incomplete_layout);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mdisplayTasks.size();
    }



}
