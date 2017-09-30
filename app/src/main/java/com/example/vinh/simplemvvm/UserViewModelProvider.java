package com.example.vinh.simplemvvm;

public class UserViewModelProvider {
    public UserViewModel get(int position) {
        // Should have UserProvider for complex case.
        return new UserViewModel(new User("User " + position));
    }
}
