package com.example.vinh.simplemvvm;

import java.util.ArrayList;
import java.util.List;

public class UserListViewModel {
    private OnCreateUserListener onCreateUserListener;

    public interface OnCreateUserListener {
        void onUserCreated(User user);
    }

    public List<User> getInitialUsers() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            users.add(new User("User " + i));
        }
        return users;
    }

    public void setOnCreateUserListener(OnCreateUserListener onCreateUserListener) {
        this.onCreateUserListener = onCreateUserListener;
    }

    public void createUser(int position) {
        onCreateUserListener.onUserCreated(new User("User " + position));
    }
}
