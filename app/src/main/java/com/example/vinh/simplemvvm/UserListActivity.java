package com.example.vinh.simplemvvm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.example.vinh.simplemvvm.databinding.ActivityMainBinding;

public class UserListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        UserListVM userListVM = new UserListVM(this);
        binding.setUserListVM(userListVM);

        binding.rvRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        UserAdapter userAdapter = new UserAdapter(this, userListVM);
        binding.rvRecyclerView.setAdapter(userAdapter);
    }
}
