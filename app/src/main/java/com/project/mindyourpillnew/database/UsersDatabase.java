package com.project.mindyourpillnew.database;

import com.project.mindyourpillnew.entity.User;

import java.util.ArrayList;
import java.util.HashMap;

public class UsersDatabase {
    private ArrayList<User> users = new ArrayList<>();

    public UsersDatabase() {
        initUsers();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void initUsers() {
        users.add(new User("user1", "user1@gmail.com", "password1"));
        users.add(new User("user2", "user2@gmail.com", "password2"));
        users.add(new User("user3", "user3@gmail.com", "password3"));
    }

    public void addUser(String username, String email, String password) {
        User user = new User(username, email, password);
        users.add(user);
    }

    public boolean checkUser(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

}
