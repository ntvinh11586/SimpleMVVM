package com.example.vinh.simplemvvm;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vinh.simplemvvm.databinding.UserItemBinding;

/**
 * Created by vinh on 2/10/17.
 */

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements UserVM.OnItemClickListener {
    private UserListVM userListVM;
    private Context context;

    public UserAdapter(Context context, UserListVM userListVM) {
        this.context = context;
        this.userListVM = userListVM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        UserItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.user_item, parent, false
        );
        return new UserVH(binding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        UserVH userVH = (UserVH) holder;
        UserVM userVM = userListVM.getUserVM(position);

        userVH.binding.setUserVM(userVM);
        userVM.setOnItemClickListener(this);
        userVH.binding.executePendingBindings();   // update the view now
    }

    @Override
    public int getItemCount() {
        return userListVM.size();
    }

    @Override
    public void onClickFinished(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
