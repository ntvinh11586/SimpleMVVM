package com.example.vinh.simplemvvm;

import android.support.v7.widget.RecyclerView;

import com.example.vinh.simplemvvm.databinding.UserItemBinding;

public class UserViewHolder extends RecyclerView.ViewHolder {
    private UserItemBinding binding;

    public UserViewHolder(UserItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(UserViewModel userViewModel) {
        binding.setUserViewModel(userViewModel);
        binding.executePendingBindings();
    }
}