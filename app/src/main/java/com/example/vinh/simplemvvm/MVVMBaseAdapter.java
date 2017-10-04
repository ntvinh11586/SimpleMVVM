package com.example.vinh.simplemvvm;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public abstract class MVVMBaseAdapter extends RecyclerView.Adapter<MVVMBaseViewHolder> {
    @Override
    public MVVMBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false);
        return new MVVMBaseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MVVMBaseViewHolder holder, int position) {
        Object obj = getItem(position);
        holder.bind(obj);
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutId(position);
    }

    public abstract Object getItem(int position);

    public abstract int getLayoutId(int position);
}
