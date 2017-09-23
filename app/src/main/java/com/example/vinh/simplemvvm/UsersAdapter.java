package com.example.vinh.simplemvvm;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vinh.simplemvvm.databinding.UserItemBinding;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements UserViewModel.OnUserItemClickListener {
    private List<User> users;
    private Context context;

    public UsersAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
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
        UserViewModel viewModel = new UserViewModel(users.get(position));

        userViewHolder.binding.setUserViewModel(viewModel);
        viewModel.setOnUserItemClickListener(this);

        userViewHolder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public void onUserItemClick(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void addUser(User user) {
        users.add(user);
        notifyItemChanged(getItemCount() - 1);
    }
}
