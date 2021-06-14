package com.example.TestProject.controller.services;

import com.example.TestProject.model.dao.UserDAO;
import com.example.TestProject.model.entity.User;

public class UserService {

    public boolean registration(User user) {
        UserDAO userDAO = new UserDAO();
        return !userDAO.registerUser(user);
    }
}
