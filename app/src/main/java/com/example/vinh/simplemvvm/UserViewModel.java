package com.example.vinh.simplemvvm;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;
import android.widget.Toast;

/**
 * Created by vinh on 2/10/17.
 */

public class UserViewModel extends BaseObservable {
    private User user;
    private Context context;
    private AddingDataListener addingDataListener;

    public UserViewModel(Context context, User user, AddingDataListener listener) {
        this.user = user;
        this.context = context;
        addingDataListener = listener;
    }

    @Bindable
    public String getName() {
        return user.getName();
    }

    public void setName() {
        user.setName(user.getName() + " click!");
        notifyPropertyChanged(BR.name);
    }

    public View.OnClickListener onClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get name
                Toast.makeText(context, getName(), Toast.LENGTH_SHORT).show();

                // set name with click!
                setName();

                // fire a new item
                addingDataListener.onAddingCompleted(new User("new user"));
            }
        };
    }
}
