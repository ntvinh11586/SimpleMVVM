package com.example.vinh.simplemvvm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.vinh.simplemvvm.databinding.ActivityMainBinding;

public class UserListActivity extends AppCompatActivity
        implements UsersAdapter.OnItemClickListener {
    private UserListViewModel userListViewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Inject
        userListViewModel = ((AppApplication) getApplication())
                .getUserListViewModel();

        binding.rvRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        UsersAdapter userAdapter = new UsersAdapter(this, userListViewModel);
        binding.rvRecyclerView.setAdapter(userAdapter);
        userAdapter.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_user:
                userListViewModel.createUser();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemClick(int position, UserViewModel userViewModel) {
        Toast.makeText(this, userViewModel.getUser().getName(), Toast.LENGTH_SHORT).show();
    }
}
