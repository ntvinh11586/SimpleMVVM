package com.example.vinh.simplemvvm;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vinh.simplemvvm.databinding.UserItemBinding;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements UserViewModel.OnUserClickListener {
    private ArrayList<UserViewModel> userViewModels;
    private Context context;

    public UserAdapter(Context context, ArrayList<UserViewModel> userViewModels) {
        this.context = context;
        this.userViewModels = userViewModels;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        UserItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.user_item, parent, false
        );
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        UserViewHolder userViewHolder = (UserViewHolder) holder;
        UserViewModel userViewModel = userViewModels.get(position);
        userViewHolder.binding.setUserViewModel(userViewModel);
        userViewHolder.binding.executePendingBindings();   // update the view now
        userViewModel.setOnUserClickListener(this);
    }

    @Override
    public int getItemCount() {
        return userViewModels.size();
    }

    @Override
    public void onClickFinished(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
