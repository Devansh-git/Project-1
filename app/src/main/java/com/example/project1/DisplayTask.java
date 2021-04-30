package com.example.project1;

public class DisplayTask {

    int id;
    String Task ;
    String TaskDate ;
    int TaskTypeLogo;
    Boolean isCompleted ;

    public int getId() {
        return id;
    }

    public String getTask() {
        return Task;
    }

    public String getTaskDate() {
        return TaskDate;
    }

    public int getTaskTypeLogo() {
        return TaskTypeLogo;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public DisplayTask(int id, String task, String taskDate, int taskTypeLogo, Boolean isCompleted) {
        this.id = id;
        Task = task;
        TaskDate = taskDate;
        TaskTypeLogo = taskTypeLogo;
        this.isCompleted = isCompleted;
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

    public void setTaskTypeLogo(int taskTypeLogo) {
        TaskTypeLogo = taskTypeLogo;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }
}
