package com.example.vinh.simplemvvm;

import android.content.Context;
import android.databinding.ObservableArrayList;

/**
 * Created by vinh on 2/10/17.
 */

public class UserListVM {
    private ObservableArrayList<UserVM> userVMs = new ObservableArrayList<UserVM>();

    // generate only 3 user items
    public UserListVM(Context context) {
        for (int i = 0; i < 3; i++) {
            userVMs.add(new UserVM("User " + i));
        }
    }

    public UserVM getUserVM(int position) {
        return userVMs.get(position);
    }

    public int size() {
        return userVMs.size();
    }
}
