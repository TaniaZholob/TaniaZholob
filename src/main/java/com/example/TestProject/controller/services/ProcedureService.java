package com.example.TestProject.controller.services;

import com.example.TestProject.model.dao.ProcedureDAO;
import com.example.TestProject.model.entity.Procedure;

import java.util.ArrayList;
import java.util.List;

public class ProcedureService {
    public List<Procedure> getProcedures() {
        return new ProcedureDAO().getAllProcedures();

    }
}
