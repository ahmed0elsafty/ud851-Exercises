package com.example.android.todolist;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;

import com.example.android.todolist.database.AppDatabase;

public class AddTaskViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    AppDatabase database;
    int taskId;

    public AddTaskViewModelFactory(AppDatabase database, int taskId) {
        this.database = database;
        this.taskId = taskId;
    }
    // Note: This can be reused with minor modifications
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new AddTaskViewModel(database, taskId);
    }
}
