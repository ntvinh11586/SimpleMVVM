package com.example.vinh.simplemvvm;

import android.databinding.ObservableArrayList;

import java.util.ArrayList;

public class UsersViewModel {
    private ObservableArrayList<UserViewModel> userViewModels;
    private AddUserListener addUserListener;

    public interface AddUserListener {
        void onFinished(int position);
    }

    // generate only 3 user items
    public UsersViewModel() {
        userViewModels = new ObservableArrayList<>();
        for (int i = 0; i < 3; i++) {
            userViewModels.add(new UserViewModel(
                    new User("User " + i)));
        }
    }

    public void setAddUserListener(AddUserListener addUserListener) {
        this.addUserListener = addUserListener;
    }

    public void addUser() {
        userViewModels.add(new UserViewModel(
                new User("User " + userViewModels.size())));
        if (addUserListener != null) {
            addUserListener.onFinished(userViewModels.size() - 1);
        }
    }

    public ArrayList<UserViewModel> getUserViewModels() {
        return userViewModels;
    }
}
