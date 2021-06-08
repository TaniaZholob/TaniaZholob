package com.example.TestProject.model;

import com.example.TestProject.model.entity.User;

public enum Role {
    ADMIN, CLIENT, MASTER;

    public static Role getRole(User user) {
        int roleId = user.getRoleId();
        return Role.values()[roleId];
    }

    public String getName() {
        return name().toLowerCase();
    }
}
