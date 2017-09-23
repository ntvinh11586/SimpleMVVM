package com.example.vinh.simplemvvm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.example.vinh.simplemvvm.databinding.ActivityMainBinding;

public class UserListActivity extends AppCompatActivity
        implements UsersViewModel.AddUserListener {

    private UserAdapter userAdapter;
    private UsersViewModel usersViewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        usersViewModel = new UsersViewModel();
        binding.setUsersViewModel(usersViewModel);

        binding.rvRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        userAdapter = new UserAdapter(this, usersViewModel.getUserViewModels());
        binding.rvRecyclerView.setAdapter(userAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        usersViewModel.setAddUserListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_user:
                usersViewModel.addUser();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onFinished(int position) {
        userAdapter.notifyItemInserted(position);
    }
}
