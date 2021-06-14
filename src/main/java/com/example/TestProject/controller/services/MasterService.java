package com.example.TestProject.controller.services;

import com.example.TestProject.model.dao.MasterDAO;
import com.example.TestProject.model.entity.Master;

import java.util.Comparator;
import java.util.List;

public class MasterService {

    public List<Master> sort(String sort){
        List<Master>masters = new MasterDAO().getAllMasters();
        switch (sort) {
            case "name":
                masters.sort(Comparator.comparing(Master::getName));
                break;
            case "rating":
                masters.sort(Comparator.comparing(Master::getRating).reversed());
                break;
        }
        return masters;
    }

}
