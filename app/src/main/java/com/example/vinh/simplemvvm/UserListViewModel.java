package com.example.vinh.simplemvvm;

import android.databinding.ObservableList;

public class UserListViewModel implements UserViewModel.OnClickListener {
    private OnCreateUserListener onCreateUserListener;
    private OnItemClickListener onItemClickListener;
    private final ObservableList<UserViewModel> userViewModels;

    public interface OnItemClickListener {
        void onUserItemClick(int position, User user);
    }

    public interface OnCreateUserListener {
        void onUserCreated(int position, User user);
    }

    public UserListViewModel(ObservableList<UserViewModel> userViewModels) {
        this.userViewModels = userViewModels;
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
    public void onClick(User user) {
        onItemClickListener.onUserItemClick(
                findUserViewModelPosition(user),
                user
        );
    }

    private int findUserViewModelPosition(User user) {
        for (int pos = 0; pos < userViewModels.size(); pos++) {
            if (userViewModels.get(pos).contain(user)) {
                return pos;
            }
        }
        return -1;
    }

    public UserViewModel getUserViewModel(int position) {
        return userViewModels.get(position);
    }

    public int getUserViewModelsSize() {
        return userViewModels.size();
    }

    public void createUser() {
        User user = new User("User " + userViewModels.size());
        UserViewModel userViewModel = new UserViewModel(user);
        if (onItemClickListener != null) {
            userViewModel.setOnClickListener(this);
        }
        userViewModels.add(userViewModel);
        onCreateUserListener.onUserCreated(
                findUserViewModelPosition(user),
                user
        );
    }
}
