package com.example.TestProject.controller.validator;

import com.example.TestProject.model.entity.User;

public class ValidatorData {

    private static final String EMAIL_REGEXP = "^(?=.{3,30}$)[^\\s]+@[^\\s]+\\.[^\\s]+$";
    private static final String PASSWORD_REGEXP = "^[^\\s]{6,18}$";
    private static final String NAME_REGEXP = "^\\p{Upper}[^\\s]{2,15}$";
    private static final String SURNAME_REGEXP = "^\\p{Upper}[^\\s]{2,15}$";


    public static boolean registrationValidation(User user){
        String email = user.getLogin();
        String password = user.getPassword();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();

        return (email == null || email.length() == 0 || email.matches(EMAIL_REGEXP))
                && firstName != null && firstName.matches(NAME_REGEXP)
                && password != null && password.matches(PASSWORD_REGEXP)
                && lastName != null && lastName.matches(SURNAME_REGEXP);
    }
}
