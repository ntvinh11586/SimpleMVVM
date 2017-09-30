package com.example.vinh.simplemvvm;

import android.app.Application;
import android.support.annotation.NonNull;

public class AppApplication extends Application {
    @NonNull
    public UserViewModelProvider getUserViewModelProvider() {
        return new UserViewModelProvider();
    }

    @NonNull
    public UserListViewModel getUserListViewModel() {
        return new UserListViewModel(getUserViewModelProvider());
    }
}
