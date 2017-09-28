package com.example.vinh.simplemvvm;

public class UserProvider {
    public User createUser(int position) {
        return new User("User " + position);
    }
}
