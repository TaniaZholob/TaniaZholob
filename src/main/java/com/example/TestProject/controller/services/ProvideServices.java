package com.example.TestProject.controller.services;

public class ProvideServices {
    private static final ProvideServices instance = new ProvideServices();

    private ProvideServices() {
    }

    private final UserService userService = new UserService();

    public static ProvideServices getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }
}
