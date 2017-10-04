package com.example.vinh.simplemvvm;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

public class MVVMBaseViewHolder extends RecyclerView.ViewHolder {
    private final ViewDataBinding binding;

    public MVVMBaseViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Object object) {
        binding.setVariable(BR.object, object);
    }
}
