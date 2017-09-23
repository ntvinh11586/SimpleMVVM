package com.example.vinh.simplemvvm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.example.vinh.simplemvvm.databinding.ActivityMainBinding;

public class UserListActivity extends AppCompatActivity
        implements UserListViewModel.OnCreateUserListener {

    private UsersAdapter userAdapter;
    private UserListViewModel userListViewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        userListViewModel = new UserListViewModel();
        binding.setUserListViewModel(userListViewModel);

        binding.rvRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        userAdapter = new UsersAdapter(this, userListViewModel.getInitialUsers());
        binding.rvRecyclerView.setAdapter(userAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        userListViewModel.setOnCreateUserListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_user:
                userListViewModel.createUser(
                        userAdapter.getItemCount()
                );
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onUserCreated(User user) {
        userAdapter.addUser(user);
    }
}
