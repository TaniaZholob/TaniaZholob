package com.example.TestProject.db.entity;

import java.util.Objects;

/**
 * User entity.
 *
 * @author T.Zholob
 */

public class User extends Entity {
    private static final long serialVersionUID = -6889036256149495388L;
    /**
     * Must be deleted
     **/
   /* public User(String login, String password, String firstName, String lastName, String localeName, int roleId) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.localeName = localeName;
        this.roleId = roleId;
    }*/

    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String localeName;
    private int roleId;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLocaleName() {
        return localeName;
    }

    public void setLocaleName(String localeName) {
        this.localeName = localeName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int role_id) {
        this.roleId = role_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return login.equals(user.login) && password.equals(user.password) && firstName.equals(user.firstName) && lastName.equals(user.lastName) && Objects.equals(localeName, user.localeName) && roleId == user.roleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, firstName, lastName, localeName, roleId);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", localeName='" + localeName + '\'' +
                ", roleId=" + roleId +
                '}';
    }
}
