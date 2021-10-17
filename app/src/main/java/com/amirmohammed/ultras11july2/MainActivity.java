package com.amirmohammed.ultras11july2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.amirmohammed.ultras11july2.room.Task;
import com.amirmohammed.ultras11july2.room.TasksDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Task task = new Task("Title 1", "17/10/21", "8:44 PM");

//        TasksDatabase.getInstance(this).tasksDao().insertTask(task);

        List<Task> taskList = TasksDatabase.getInstance(this).tasksDao().getTasks();

        Task task1 = taskList.get(0);

        System.out.println(task1.getId());

        task1.setStatusDone();

        TasksDatabase.getInstance(this).tasksDao().updateTask(task1);

    }




}