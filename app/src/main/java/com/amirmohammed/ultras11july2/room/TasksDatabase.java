package com.amirmohammed.ultras11july2.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Task.class}, version = 1, exportSchema = false)
public abstract class TasksDatabase extends RoomDatabase {

    public abstract ITasksDao tasksDao();

    private static TasksDatabase tasksDatabase;

    public static TasksDatabase getInstance(Context context) {
        if (tasksDatabase == null) {
            tasksDatabase = Room.databaseBuilder(context,
                    TasksDatabase.class, "tasksDatabase")
                    .allowMainThreadQueries()
                    .build();
        }
        return tasksDatabase;
    }

}
