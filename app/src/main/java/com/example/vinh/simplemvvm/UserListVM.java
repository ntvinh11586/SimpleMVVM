package com.example.vinh.simplemvvm;

import android.content.Context;
import android.databinding.ObservableArrayList;

/**
 * Created by vinh on 2/10/17.
 */

public class UserListVM {
    private ObservableArrayList<UserVM> userVMs = new ObservableArrayList<UserVM>();
    private OnAddNewUserListener onAddNewUserListener;

    public interface OnAddNewUserListener {
        public void onAddNewUserFinished(int position);
    }

    // generate only 3 user items
    public UserListVM(Context context) {
        for (int i = 0; i < 3; i++) {
            userVMs.add(new UserVM("User " + i));
        }
    }

    public void setOnAddNewUserListener(OnAddNewUserListener onAddNewUserListener) {
        this.onAddNewUserListener = onAddNewUserListener;
    }

    public UserVM getUserVM(int position) {
        return userVMs.get(position);
    }

    public int size() {
        return userVMs.size();
    }

    public void addNewUserVM() {
        userVMs.add(new UserVM("User " + userVMs.size()));
        if (onAddNewUserListener != null) {
            onAddNewUserListener.onAddNewUserFinished(userVMs.size() - 1);
        }
    }
}
