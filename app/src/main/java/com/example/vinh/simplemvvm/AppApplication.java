package com.example.vinh.simplemvvm;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

public class AppApplication extends Application {
    @NonNull
    public User getUser(String name) {
        return new User(name);
    }

    @NonNull
    public UserProvider getUserProvider() {
        return new UserProvider();
    }

    @NonNull
    public ObservableList<UserViewModel> getUserViewModels() {
        ObservableList<UserViewModel> userViewModels = new ObservableArrayList<>();
        for (int pos = 0; pos < 5; pos++) {
            User user = getUser("User " + pos);
            userViewModels.add(new UserViewModel(user));
        }
        return userViewModels;
    }

    @NonNull
    public UserListViewModel getUserListViewModel() {
        return new UserListViewModel(getUserProvider(), getUserViewModels());
    }
}
