package com.project.mindyourpillnew;

import com.project.mindyourpillnew.database.UsersDatabase;
import com.project.mindyourpillnew.entity.User;

import java.util.ArrayList;

public class UserRepository {

    private ArrayList<User> users;
    private UsersDatabase usersDatabase = new UsersDatabase();

    public ArrayList<User> getUsers() {
        return usersDatabase.getUsers();
    }

    public boolean checkUser(String email, String password) {
        return usersDatabase.checkUser(email, password);
    }

    public void addUser(String username, String email,  String password) {
        usersDatabase.addUser(username, email, password);
    }

    public User getUser(String email, String password) {
        for (User user : usersDatabase.getUsers()) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
