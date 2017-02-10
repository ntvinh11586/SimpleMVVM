package com.example.vinh.simplemvvm;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.vinh.simplemvvm.databinding.UserItemBinding;

import java.util.ArrayList;

/**
 * Created by vinh on 2/10/17.
 */

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<User> users;
    private Context context;

    public UserAdapter(Context context, ArrayList<User> users) {
        this.users = users;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        UserItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.user_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.binding.setUserViewModel(new UserViewModel(context, users.get(position)));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        UserItemBinding binding;

        public ViewHolder(UserItemBinding binding) {
            super(binding.llUsers);
            this.binding = binding;
        }
    }
}
