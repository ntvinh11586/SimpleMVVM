package com.example.vinh.simplemvvm;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class UserListViewModel implements UserViewModel.OnClickListener {
    private OnCreateUserListener onCreateUserListener;
    private OnItemClickListener onItemClickListener;
    private final ObservableList<UserViewModel> userViewModels = new ObservableArrayList<>();
    private final UserViewModelProvider userViewModelProvider;

    public interface OnItemClickListener {
        void onUserItemClick(int position, UserViewModel userViewModel);
    }

    public interface OnCreateUserListener {
        void onUserCreated(int position, User user);
    }

    public UserListViewModel(UserViewModelProvider userViewModelProvider) {
        this.userViewModelProvider = userViewModelProvider;
    }

    public void setOnCreateUserListener(OnCreateUserListener onCreateUserListener) {
        this.onCreateUserListener = onCreateUserListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        for (UserViewModel userViewModel : userViewModels) {
            userViewModel.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(UserViewModel userViewModel) {
        int position = findUserViewModelPosition(userViewModel);
        if (position != -1) {
            onItemClickListener.onUserItemClick(
                    findUserViewModelPosition(userViewModel),
                    userViewModel
            );
        }
    }

    private int findUserViewModelPosition(UserViewModel userViewModel) {
        for (int pos = 0; pos < userViewModels.size(); pos++) {
            if (userViewModels.get(pos).equals(userViewModel)) {
                return pos;
            }
        }
        return -1;
    }

    public UserViewModel getUserViewModel(int position) {
        return userViewModels.get(position);
    }

    public List<UserViewModel> getUserViewModels() {
        List<UserViewModel> viewModels = new ArrayList<>();
        for (UserViewModel userViewModel : userViewModels) {
            viewModels.add(userViewModel);
        }
        return viewModels;
    }

    public void createUser() {
        UserViewModel userViewModel = userViewModelProvider.get(userViewModels.size());
        userViewModels.add(userViewModel);

        if (onItemClickListener != null) {
            userViewModel.setOnClickListener(this);
        }

        if (onCreateUserListener != null) {
            onCreateUserListener.onUserCreated(
                    userViewModels.size() - 1,
                    userViewModel.getUser()
            );
        }
    }
}
