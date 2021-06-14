package com.example.TestProject.controller.services;

import com.example.TestProject.model.dao.ProcedureDAO;
import com.example.TestProject.model.entity.Procedure;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProcedureService {
    public List<Procedure> getProcedures() {
        return new ProcedureDAO().getAllProcedures();
    }

    public List<Procedure> filter(String filterCommand, List<Procedure> procedures){
        switch (filterCommand) {
            case "a":
                procedures = procedures.stream().filter(procedure -> procedure.getPrice() <= 400).collect(Collectors.toList());
                break;
            case "b":
                procedures = procedures.stream().filter(procedure -> (procedure.getPrice() > 400 && procedure.getPrice() <= 600)).collect(Collectors.toList());
                break;
            case "c":
                procedures = procedures.stream().filter(procedure -> procedure.getPrice() > 600).collect(Collectors.toList());
                break;
        }
        return procedures;
    }

}
