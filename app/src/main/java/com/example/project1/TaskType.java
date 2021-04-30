package com.example.project1;

public class TaskType {

    private String taskType;
    private int taskImage;

    public TaskType(String taskType, int taskImage) {
        this.taskType = taskType;
        this.taskImage = taskImage;
    }

    public String getTaskType() {
        return taskType;
    }

    public int getTaskImage() {
        return taskImage;
    }
}
