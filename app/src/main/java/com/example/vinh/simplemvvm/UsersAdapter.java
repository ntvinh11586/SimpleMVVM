package com.example.vinh.simplemvvm;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.vinh.simplemvvm.databinding.UserItemBinding;

public class UsersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements UserListViewModel.OnItemClickListener,
        UserListViewModel.OnCreateUserListener {
    private OnItemClickListener onItemClickListener;
    private final UserListViewModel userListViewModel;

    public interface OnItemClickListener {
        void onItemClick(int position, UserViewModel userViewModel);
    }

    public UsersAdapter(UserListViewModel userListViewModel) {
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        UserItemBinding binding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.user_item,
                        parent,
                        false
                );
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        UserViewHolder userViewHolder = (UserViewHolder) holder;
        userViewHolder.bind(
                userListViewModel.getUserViewModel(position)
        );
    }

    @Override
    public int getItemCount() {
        return userListViewModel.getUserViewModels().size();
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
