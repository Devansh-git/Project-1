package com.example.project1.Adapters;

import android.content.Context;
import android.content.Entity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project1.DisplayTask;
import com.example.project1.R;
import com.example.project1.TaskType;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{

    private ArrayList<DisplayTask> mdisplayTasks;

    public static class TaskViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView ivTaskType;
        public TextView Task,TaskDate;
        public Switch switchIsComplete;

        public TaskViewHolder(View taskView){
            super(taskView);
            ivTaskType = taskView.findViewById(R.id.taskTypeDisplay);
            Task = taskView.findViewById(R.id.Task);
            TaskDate = taskView.findViewById(R.id.taskCompletionDate);

            switchIsComplete = taskView.findViewById(R.id.taskIsComplete);

        }


    }

    public TaskAdapter(ArrayList<DisplayTask> displayTasks)
    {
        mdisplayTasks = displayTasks;
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

        holder.ivTaskType.setImageResource(dt.getTaskTypeLogo());
        holder.Task.setText(dt.getTask());
        holder.TaskDate.setText(dt.getTaskDate());
        holder.switchIsComplete.setChecked((dt.getCompleted()));

    }

    @Override
    public int getItemCount() {
        return mdisplayTasks.size();
    }



}
