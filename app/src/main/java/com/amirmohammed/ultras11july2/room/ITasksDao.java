package com.amirmohammed.ultras11july2.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ITasksDao {

    @Query("SELECT * FROM tasks WHERE status = 'active' ")
    List<Task> getActiveTasks();

    @Query("SELECT * FROM tasks WHERE status = 'done' ")
    List<Task> getDoneTasks();

    @Query("SELECT * FROM tasks WHERE status = 'archive' ")
    List<Task> getArchiveTasks();


    @Query("DELETE FROM tasks")
    void deleteAll();

    @Insert
    void insertTask(Task task);

    @Delete
    void deleteTask(Task task);

    @Update
    void updateTask(Task task);

}
