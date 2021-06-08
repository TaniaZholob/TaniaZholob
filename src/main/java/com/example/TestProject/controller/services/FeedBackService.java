package com.example.TestProject.controller.services;

import com.example.TestProject.model.dao.MasterDAO;
import com.example.TestProject.model.dao.ReviewDAO;
import com.example.TestProject.model.entity.Master;
import com.example.TestProject.model.entity.User;

public class FeedBackService {

    public boolean addRating(int rating, String text, String master, User user) {
        MasterDAO masterDAO = new MasterDAO();
        String[] nameSurname = master.split(" ");
        Master master1 = masterDAO.findMaster(nameSurname[0], nameSurname[1]);
        ReviewDAO reviewDAO = new ReviewDAO();
        return !reviewDAO.addNewReview(master1, user, text, rating);
    }
}
