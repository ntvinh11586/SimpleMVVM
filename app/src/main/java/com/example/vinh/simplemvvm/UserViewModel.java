package com.example.vinh.simplemvvm;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

public class UserViewModel extends BaseObservable {
    private User user;
    private OnUserClickListener onUserClickListener;

    public interface OnUserClickListener {
        void onClickFinished(String message);
    }

    public UserViewModel(User user) {
        this.user = user;
    }

    public void setOnUserClickListener(OnUserClickListener onUserClickListener) {
        this.onUserClickListener = onUserClickListener;
    }

    @Bindable
    public String getName() {
        return user.getName();
    }

    public void setName(String name) {
        user.setName(name);
        notifyPropertyChanged(com.example.vinh.simplemvvm.BR.name);
    }

    public View.OnClickListener onClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setName(user.getName() + " Click!");
                onUserClickListener.onClickFinished(user.getName());
            }
        };
    }
}
