package com.example.vinh.simplemvvm;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class UserViewModel extends BaseObservable {
    private OnUserItemClickListener onUserItemClickListener;
    private User user;

    public interface OnUserItemClickListener {
        void onUserItemClick(String message);
    }

    public UserViewModel(User user) {
        this.user = user;
    }

    public void setOnUserItemClickListener(OnUserItemClickListener onUserItemClickListener) {
        this.onUserItemClickListener = onUserItemClickListener;
    }

    @Bindable
    public String getName() {
        return user.getName();
    }

    public void setName(String name) {
        user.setName(name);
        notifyPropertyChanged(com.example.vinh.simplemvvm.BR.name);
    }

    public void onClick() {
        onUserItemClickListener.onUserItemClick(user.getName());
    }
}
