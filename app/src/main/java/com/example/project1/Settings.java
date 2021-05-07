package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.project1.Database.TaskManager;

public class Settings extends AppCompatActivity {

    TaskManager tm ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        try {
            tm = new TaskManager(this);
        }catch (Exception e){
            Log.e("Error : ",  e.getMessage() );
        }
    }

    public void DeleteAll(View view) {
        tm.deleteAll();
        Toast.makeText(this, "All Tasks Deleted", Toast.LENGTH_LONG).show();

    }

    public void goBack(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}