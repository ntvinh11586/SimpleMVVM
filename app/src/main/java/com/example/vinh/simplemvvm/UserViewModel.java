package com.example.vinh.simplemvvm;

import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;
import android.widget.Toast;

/**
 * Created by vinh on 2/10/17.
 */

public class UserViewModel extends BaseObservable {
    private User user;
    private Context context;

    public UserViewModel(Context context, User user) {
        this.user = user;
        this.context = context;
    }

    public String getName() {
        return user.getName();
    }

    public View.OnClickListener onClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, getName(), Toast.LENGTH_SHORT).show();
            }
        };
    }
}
