package com.amirmohammed.ultras11july2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.amirmohammed.ultras11july2.room.Task;
import com.amirmohammed.ultras11july2.room.TasksDatabase;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;


public class InsertTaskSheetFragment extends BottomSheetDialogFragment {
    EditText editTextTitle, editTextDate, editTextTime;
    MaterialButton materialButtonInsert;
    IInsertTask iInsertTask;

    public InsertTaskSheetFragment(IInsertTask iInsertTask) {
        this.iInsertTask = iInsertTask;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_insert_task_sheet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextTitle = view.findViewById(R.id.insert_et_title);
        editTextDate = view.findViewById(R.id.insert_et_date);
        editTextTime = view.findViewById(R.id.insert_et_time);
        materialButtonInsert = view.findViewById(R.id.btn_insert_task);

        materialButtonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTextTitle.getText().toString();
                String date = editTextDate.getText().toString();
                String time = editTextTime.getText().toString();

                Task task = new Task(title, date, time);

                TasksDatabase.getInstance(requireContext()).tasksDao().insertTask(task);

                iInsertTask.onTaskInserted();

                dismiss();
            }
        });

    }


}