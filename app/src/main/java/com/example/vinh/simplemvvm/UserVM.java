package com.example.vinh.simplemvvm;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

/**
 * Created by vinh on 2/12/17.
 */

public class UserVM extends BaseObservable {
    private User user;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        public void onClickFinished(String message);
    }

    public UserVM(String name) {
        user = new User(name);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Bindable
    public String getName() {
        return user.getName();
    }

    public void setName(String name) {
        user.setName(name);
    }

    public View.OnClickListener onClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setName(user.getName() + " Click!");
                notifyPropertyChanged(BR.name);
                onItemClickListener.onClickFinished(user.getName());
            }
        };
    }
}
