package com.amirmohammed.ultras11july2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amirmohammed.ultras11july2.room.Task;
import com.amirmohammed.ultras11july2.room.TasksDatabase;

import java.util.ArrayList;
import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TaskHolder> {

    List<Task> taskArrayList = new ArrayList<>();

    public TasksAdapter(List<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
        Task task = taskArrayList.get(position);

        holder.textViewTitle.setText(position);
        holder.textViewDate.setText("Date : " + task.getDate());
        holder.textViewTime.setText("Time : " + task.getTime());

        holder.imageViewDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task.setStatusDone();
                TasksDatabase.getInstance(v.getContext()).tasksDao().updateTask(task);
                taskArrayList.remove(position);
                notifyItemRemoved(position);
            }
        });

        holder.imageViewArchive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task.setStatusArchive();
                TasksDatabase.getInstance(v.getContext()).tasksDao().updateTask(task);
                taskArrayList.remove(position);
                notifyItemRemoved(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskArrayList.size();
    }


    class TaskHolder extends RecyclerView.ViewHolder {
        ImageView imageViewDone, imageViewArchive;
        TextView textViewTitle, textViewDate, textViewTime;

        public TaskHolder(@NonNull View itemView) {
            super(itemView);

            imageViewDone = itemView.findViewById(R.id.done);
            imageViewArchive = itemView.findViewById(R.id.archive);
            textViewTitle = itemView.findViewById(R.id.task_title);
            textViewDate = itemView.findViewById(R.id.task_date);
            textViewTime = itemView.findViewById(R.id.task_time);
        }

    }
}
