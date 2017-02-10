package com.example.vinh.simplemvvm;

import android.support.v7.widget.RecyclerView;

import com.example.vinh.simplemvvm.databinding.UserItemBinding;

public class UserVH extends RecyclerView.ViewHolder {
    UserItemBinding binding;

    public UserVH(UserItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}