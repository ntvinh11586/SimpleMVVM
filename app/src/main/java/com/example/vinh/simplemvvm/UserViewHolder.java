package com.example.vinh.simplemvvm;

import android.support.v7.widget.RecyclerView;

import com.example.vinh.simplemvvm.databinding.UserItemBinding;

public class UserViewHolder extends RecyclerView.ViewHolder {
    UserItemBinding binding;

    public UserViewHolder(UserItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}