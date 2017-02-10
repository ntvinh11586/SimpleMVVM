package com.example.vinh.simplemvvm;

import java.util.ArrayList;

/**
 * Created by vinh on 2/10/17.
 */

public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    // where to get mock data, data get from retrofit, persistence,...
    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("vinh 1"));
        users.add(new User("vinh 2"));
        users.add(new User("vinh 3"));
        users.add(new User("vinh 4"));
        return users;
    }

    public String getName() {
        return name;
    }
}
