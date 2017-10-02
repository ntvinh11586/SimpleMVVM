package com.example.vinh.simplemvvm;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class UserViewModel extends BaseObservable {
    private OnClickListener onClickListener;
    private User user;

    public boolean contain(User user) {
        return this.user.equals(user);
    }

    public interface OnClickListener{
        void onClick(UserViewModel userViewModel);
    }

    public UserViewModel(User user) {
        this.user = user;
    }

    @Bindable
    public String getName() {
        return user.getName();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void onClick() {
        onClickListener.onClick(this);
    }
}
