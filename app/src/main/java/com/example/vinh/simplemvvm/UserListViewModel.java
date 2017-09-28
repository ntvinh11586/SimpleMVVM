package com.example.vinh.simplemvvm;

import android.databinding.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class UserListViewModel implements UserViewModel.OnClickListener {
    private OnCreateUserListener onCreateUserListener;
    private OnItemClickListener onItemClickListener;
    private final ObservableList<UserViewModel> userViewModels;
    private final UserProvider userProvider;

    public interface OnItemClickListener {
        void onUserItemClick(int position, User user);
    }

    public interface OnCreateUserListener {
        void onUserCreated(int position, User user);
    }

    public UserListViewModel(
            UserProvider userProvider,
            ObservableList<UserViewModel> userViewModels
    ) {
        this.userViewModels = userViewModels;
        this.userProvider = userProvider;
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

    public List<UserViewModel> getUserViewModels() {
        List<UserViewModel> viewModels = new ArrayList<>();
        for (UserViewModel userViewModel : userViewModels) {
            viewModels.add(userViewModel);
        }
        return viewModels;
    }

    public void createUser() {
        User user = userProvider.createUser(userViewModels.size());
        UserViewModel userViewModel = new UserViewModel(user);
        userViewModels.add(userViewModel);

        if (onItemClickListener != null) {
            userViewModel.setOnClickListener(this);
        }

        if (onCreateUserListener != null) {
            onCreateUserListener.onUserCreated(
                    findUserViewModelPosition(user),
                    user
            );
        }
    }
}
