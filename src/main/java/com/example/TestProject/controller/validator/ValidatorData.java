package com.example.TestProject.controller.validator;

import com.example.TestProject.model.entity.User;

public class ValidatorData {

    private static final String EMAIL_REGEXP = "^(?=.{3,30}$)[^\\s]+@[^\\s]+\\.[^\\s]+$";
    private static final String PASSWORD_REGEXP = "^[^\\s]{4,18}$";
    private static final String NAME_REGEXP = "^[^\\s]{2,15}$";
    private static final String SURNAME_REGEXP = "^[^\\s]{2,15}$";


    public static boolean registrationValidation(User user){

        String email = user.getLogin();
        String password = user.getPassword();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();


        System.out.println(firstName.matches(NAME_REGEXP)+" first anme");
        System.out.println(password.matches(PASSWORD_REGEXP)+" password");
        System.out.println(lastName.matches(SURNAME_REGEXP)+" surname");


        return (email == null || email.length() == 0 || email.matches(EMAIL_REGEXP))
                && firstName != null && firstName.matches(NAME_REGEXP)
                && password != null && password.matches(PASSWORD_REGEXP)
                && lastName != null && lastName.matches(SURNAME_REGEXP);
    }
}
