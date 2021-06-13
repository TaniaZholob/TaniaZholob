package com.example.TestProject.controller.services;

import com.example.TestProject.senderImail.Sender;
import com.example.TestProject.model.entity.User;

public class SenderService {
    boolean sendingLetter(User user) {
        Sender sender = new Sender("", "");
        sender.send("BeautySalon! response", "Dear " + user.getLastName() + " " + user.getFirstName() + "\n Please leave a review about our salon!\n" + "http://localhost:8080/Gradle___com_example___TestProject_1_0_SNAPSHOT_war/beautySalon", "nusha.cemp@gmail.com", user.getLogin());
        return true;
    }
}
