package com.example.TestProject.controller.services;

import com.example.TestProject.model.dao.UserDAO;
import com.example.TestProject.model.entity.User;

import java.util.Optional;

public class LogInService {
    private UserDAO userDAO = new UserDAO();

    public Optional<User> logIn(String email, String password) {

        return userDAO.findUser(email, password);
    }
}
