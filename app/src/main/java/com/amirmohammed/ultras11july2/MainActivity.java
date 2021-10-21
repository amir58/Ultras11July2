package com.amirmohammed.ultras11july2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.amirmohammed.ultras11july2.room.ITasksDao;
import com.amirmohammed.ultras11july2.room.Task;
import com.amirmohammed.ultras11july2.room.TasksDatabase;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Task> tasks;
    TasksAdapter tasksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_tasks);

        tasks = TasksDatabase.getInstance(this).tasksDao().getActiveTasks();

        tasksAdapter = new TasksAdapter(tasks);
        recyclerView.setAdapter(tasksAdapter);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);

                if (item.getItemId() == R.id.item_tasks) {
                    tasks = TasksDatabase.getInstance(MainActivity.this).tasksDao().getActiveTasks();
                    tasksAdapter = new TasksAdapter(tasks);
                    recyclerView.setAdapter(tasksAdapter);
                } else if (item.getItemId() == R.id.item_done) {
                    tasks = TasksDatabase.getInstance(MainActivity.this).tasksDao().getDoneTasks();
                    tasksAdapter = new TasksAdapter(tasks);
                    recyclerView.setAdapter(tasksAdapter);
                } else if (item.getItemId() == R.id.item_archive) {
                    tasks = TasksDatabase.getInstance(MainActivity.this).tasksDao().getArchiveTasks();
                    tasksAdapter = new TasksAdapter(tasks);
                    recyclerView.setAdapter(tasksAdapter);
                }

                return false;
            }
        });

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                Task task = tasks.get(viewHolder.getAdapterPosition());
                TasksDatabase.getInstance(MainActivity.this).tasksDao().deleteTask(task);
                tasks.remove(viewHolder.getAdapterPosition());
                tasksAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    public void insertTask(View view) {
        new InsertTaskSheetFragment(iInsertTask).show(getSupportFragmentManager(), "insertSheet");
    }

    IInsertTask iInsertTask = new IInsertTask() {
        @Override
        public void onTaskInserted() {
            tasks = TasksDatabase.getInstance(MainActivity.this).tasksDao().getActiveTasks();
            tasksAdapter = new TasksAdapter(tasks);
            recyclerView.setAdapter(tasksAdapter);

        }
    };

}