package com.example.project1;

import com.example.project1.Database.TaskManager;

public class DisplayTask {

    int id;
    String Task ;
    String TaskDate ;
    String TaskType;
    Boolean isCompleted ;
    TaskModel taskModel;


    public DisplayTask(){}

    public int getId() {
        return id;
    }

    public String getTask() {
        return Task;
    }

    public String getTaskDate() {
        return TaskDate;
    }



    public Boolean getCompleted() {
        return isCompleted;
    }

    public DisplayTask(int id, String task, String taskDate, String taskType, Boolean isCompleted,TaskModel taskModel) {
        this.id = id;
        Task = task;
        TaskDate = taskDate;
        TaskType=taskType;
        this.isCompleted = isCompleted;
        this.taskModel=taskModel;

    }

    public String getTaskType() {
        return TaskType;
    }

    public TaskModel getTaskModel() {
        return taskModel;
    }

    public void setTaskModel(TaskModel taskModel) {
        this.taskModel = taskModel;
    }

    public void setTaskType(String taskType) {
        TaskType = taskType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTask(String task) {
        Task = task;
    }

    public void setTaskDate(String taskDate) {
        TaskDate = taskDate;
    }



    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }
}
