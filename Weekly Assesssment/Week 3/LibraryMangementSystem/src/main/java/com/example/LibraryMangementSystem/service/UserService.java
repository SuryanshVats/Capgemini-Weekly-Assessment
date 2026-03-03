package com.example.LibraryMangementSystem.service;

import com.example.LibraryMangementSystem.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {
    private List<User> users = new ArrayList<>();

    public void register(User user) {
        users.add(user);
    }

    public User login(String email, String password) {
        return users.stream()
                .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
                .findFirst().orElse(null);
    }
}