package com.example.project1.Database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.project1.TaskModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TaskManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Task.DB";
    private static final int DATABASE_VERSION = 1;


    private static final String tableName = "Tasks";
    private static final String ID = "ID";
    private static final String TaskType = "TaskType";
    private static final String Task = "Task";
    private static final String CompleteDate = "CompleteDate";
    private static final String NotifyDate = "NotifyDate";
    private static final String NotifyTime = "NotifyTime";
    private static final String IsComplete = "IsComplete";
    private static String tableCreatorString ;


    public TaskManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        tableCreatorString = "CREATE TABLE "+ tableName + "(ID Integer Primary Key AUTOINCREMENT, TaskType TEXT, Task Text, CompleteDate Text, NotifyDate Text, NotifyTime Text, IsComplete Text);";

        db.execSQL(tableCreatorString);

        deleteOne(1);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        //recreate the table

        onCreate(db);
    }

    public boolean addOne(TaskModel taskModel) throws Exception{
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();


        cv.put("TaskType",taskModel.getTaskType());
        cv.put("Task",taskModel.getTask());
        cv.put("CompleteDate",taskModel.getDate());
        cv.put("NotifyDate",taskModel.getNotifyDate());
        cv.put("NotifyTime",taskModel.getNotifyTime());
        cv.put("IsComplete",taskModel.getCompleted());

        long nr = db.insert(tableName,null,cv);
        db.close();
        return nr>-1;

    }

    public ArrayList<TaskModel> getAll() throws Exception{
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor  = db.rawQuery("SELECT * FROM " + tableName,null);

        ArrayList<TaskModel> allTasks = new ArrayList<>();

        if(cursor.moveToFirst())
        {
            do {
                TaskModel tm = new TaskModel();

                tm.setId(cursor.getInt(0));
                tm.setTaskType(cursor.getString(1));
                tm.setTask(cursor.getString(2));
                tm.setDate(cursor.getString(3));
                tm.setNotifyDate(cursor.getString(4));
                tm.setNotifyTime(cursor.getString(5));
                tm.setCompleted(Integer.valueOf(cursor.getString(6)));

                allTasks.add(tm);

            }while(cursor.moveToNext());
        }else{
            allTasks=null;

        }
        db.close();

        return allTasks;
    }

    public void deleteOne(int i)
    {
        SQLiteDatabase db =this.getWritableDatabase();

        db.delete(tableName,   "ID = ? ",new String[]{String.valueOf(i)});
    }

    public TaskModel getById(int Id) throws Exception{
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor  = db.rawQuery("select * from " + tableName + " where " + ID + "=' "+ Id + "'"  ,null);
        TaskModel tm = new TaskModel();
        if(cursor.moveToFirst())
        {
            cursor.moveToFirst();
            tm.setId(cursor.getInt(0));
            tm.setTaskType(cursor.getString(1));
            tm.setTask(cursor.getString(2));
            tm.setDate(cursor.getString(3));
            tm.setNotifyDate(cursor.getString(4));
            tm.setNotifyTime(cursor.getString(5));
            tm.setCompleted(Integer.valueOf(cursor.getString(6)));


        }else
        {
            tm=null;
        }
        db.close();
        return tm;
    }

    public boolean setCompleted(TaskModel tm)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();


        cv.put("TaskType",tm.getTaskType());
        cv.put("Task",tm.getTask());
        cv.put("CompleteDate",tm.getDate());
        cv.put("NotifyDate",tm.getNotifyDate());
        cv.put("NotifyTime",tm.getNotifyTime());
        cv.put("IsComplete",1);

        int number  = db.update(tableName,cv,"ID = ?",new String[]{String.valueOf(tm.getId())});

        if(number==1)
        {
            return true;
        }
        else
        {
            return false;
        }

    }


}
