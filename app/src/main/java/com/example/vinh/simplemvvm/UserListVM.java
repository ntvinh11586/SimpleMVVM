package com.example.vinh.simplemvvm;

import android.content.Context;
import android.databinding.ObservableArrayList;

import java.util.ArrayList;

/**
 * Created by vinh on 2/10/17.
 */

public class UserListVM {
    private Context context;

    private ObservableArrayList<UserVM> userVMs;
    private OnAddNewUserListener onAddNewUserListener;

    public interface OnAddNewUserListener {
        void onAddNewUserFinished(int position);
    }

    // generate only 3 user items
    public UserListVM(Context context) {
        this.context = context;
        userVMs = new ObservableArrayList<>();
        for (int i = 0; i < 3; i++) {
            userVMs.add(new UserVM("User " + i));
        }
    }

    public void setOnAddNewUserListener(OnAddNewUserListener onAddNewUserListener) {
        this.onAddNewUserListener = onAddNewUserListener;
    }

    public void addNewUserVM() {
        userVMs.add(new UserVM("User " + userVMs.size()));
        if (onAddNewUserListener != null) {
            onAddNewUserListener.onAddNewUserFinished(userVMs.size() - 1);
        }
    }

    public ArrayList<UserVM> getUserVMs() {
        return userVMs;
    }

    public Context getContext() {
        return this.context;
    }
}
