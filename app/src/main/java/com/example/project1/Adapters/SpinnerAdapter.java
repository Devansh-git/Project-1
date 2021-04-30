
package com.example.project1.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.project1.R;
import com.example.project1.TaskModel;
import com.example.project1.TaskType;

import java.util.ArrayList;

public class SpinnerAdapter extends ArrayAdapter<TaskType> {

    public SpinnerAdapter(Context context, ArrayList<TaskType> taskTypes)
    {
         super(context,0,taskTypes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    private View initView(int position,View convertView, ViewGroup parent)
    {
        if(convertView==null)
        {
            convertView=LayoutInflater.from(getContext()).inflate(R.layout.spinnerl_layout,parent,false);


        }

        ImageView imageViewLogo =convertView.findViewById(R.id.taskTypeLogo);
        TextView taskType = convertView.findViewById(R.id.tvtaskType);

        TaskType currentItem = getItem(position);

        if(currentItem!=null) {
            imageViewLogo.setImageResource(currentItem.getTaskImage());
            taskType.setText((currentItem.getTaskType()));
        }
        return convertView;

    }
}
