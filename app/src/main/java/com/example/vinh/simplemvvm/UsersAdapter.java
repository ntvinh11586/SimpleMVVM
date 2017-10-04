package com.example.vinh.simplemvvm;

import android.support.v7.widget.RecyclerView;

public class UsersAdapter extends MVVMBaseAdapter
        implements UserListViewModel.OnItemClickListener,
        UserListViewModel.OnCreateUserListener {
    private final int layoutId;
    private final UserListViewModel userListViewModel;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position, UserViewModel userViewModel);
    }

    public UsersAdapter(int layoutId, UserListViewModel userListViewModel) {
        this.layoutId = layoutId;
        this.userListViewModel = userListViewModel;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        userListViewModel.setOnItemClickListener(this);
        userListViewModel.setOnCreateUserListener(this);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        userListViewModel.setOnItemClickListener(null);
        userListViewModel.setOnCreateUserListener(null);
        super.onDetachedFromRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return userListViewModel.getUserViewModels().size();
    }

    @Override
    public Object getItem(int position) {
        return userListViewModel.getUserViewModel(position);
    }

    @Override
    public int getLayoutId(int position) {
        return layoutId;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onUserItemClick(int position, UserViewModel userViewModel) {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(position, userViewModel);
        }
    }

    @Override
    public void onUserCreated(int position, UserViewModel userViewModel) {
        notifyItemChanged(position);
    }
}
