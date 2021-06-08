package com.example.TestProject.controller.services;

import com.example.TestProject.model.dao.UserDAO;
import com.example.TestProject.model.entity.User;

public class UserService {

    public void registration(User user) {
        UserDAO userDAO = new UserDAO();
            userDAO.registerUser(user);
    }
}
