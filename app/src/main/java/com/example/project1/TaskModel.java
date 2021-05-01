package com.example.project1;

import java.util.Date;

public class TaskModel {

    public int id;
    public String taskType;
    public String task;
    public String Date;
    public String notifyDate;
    public String notifyTime;
    public int isCompleted;

    public TaskModel(int id, String taskType, String task, String date, String notifyDate, String notifyTime, int isCompleted) {
        this.id = id;
        this.taskType = taskType;
        this.task = task;
        Date = date;
        this.notifyDate = notifyDate;
        this.notifyTime = notifyTime;
        this.isCompleted = isCompleted;
    }

    public TaskModel(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getNotifyDate() {
        return notifyDate;
    }

    public void setNotifyDate(String notifyDate) {
        this.notifyDate = notifyDate;
    }

    public String getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(String notifyTime) {
        this.notifyTime = notifyTime;
    }

    public int getCompleted() {
        return isCompleted;
    }

    public void setCompleted(int completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return "TaskModel{" +
                "id=" + id +
                ", taskType='" + taskType + '\'' +
                ", task='" + task + '\'' +
                ", Date=" + Date +
                ", notifyDate=" + notifyDate +
                ", notifyTime='" + notifyTime + '\'' +
                ", isCompleted=" + isCompleted +
                '}';
    }
}
