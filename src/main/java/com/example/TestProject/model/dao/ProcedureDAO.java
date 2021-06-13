package com.example.TestProject.model.dao;

import com.example.TestProject.model.DBManager;
import com.example.TestProject.model.EntityMapper;
import com.example.TestProject.constants.Fields;
import com.example.TestProject.model.entity.Master;
import com.example.TestProject.model.entity.Procedure;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.TestProject.constants.SQLquary.*;

public class ProcedureDAO {


    /**
     * Returns a user with the given identifier.
     *
     * @param id Procedure identifier.
     * @return Procedure entity.
     */
    public Procedure findProcedurebyID(Long id) {
        Procedure procedure = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnectionInner();
//            connection = DBManager.getInstance().getConnection();
            ProcedureMapper mapper = new ProcedureMapper();
            preparedStatement = connection.prepareStatement(SQL__FIND_PROCEDURE_BY_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                procedure = mapper.mapRow(resultSet);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException throwables) {
            DBManager.getInstance().rollbackAndClose(connection);
            throwables.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return procedure;
    }

    /**
     * Returns a user with the given identifier.
     *
     * @param  title Procedure identifier.
     * @return Procedure entity.
     */
    public Long findIdByTitle(String title) {
        Long id;
        Procedure procedure = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnectionInner();
//            connection = DBManager.getInstance().getConnection();
            ProcedureMapper mapper = new ProcedureMapper();
            preparedStatement = connection.prepareStatement(SQL__FIND_PROCEDURE_BY_TITLE);
            preparedStatement.setString(1, title);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                procedure = mapper.mapRow(resultSet);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException throwables) {
            DBManager.getInstance().rollbackAndClose(connection);
            throwables.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return procedure.getId();
    }


    public List<Procedure> getAllProcedures() {
        List<Procedure> procedures = new ArrayList<>();
        Master master = null;
        Statement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
//            connection = DBManager.getInstance().getConnectionInner();
            connection = DBManager.getInstance().getConnection();
            ProcedureMapper mapper = new ProcedureMapper();
            preparedStatement = connection.createStatement();
            resultSet = preparedStatement.executeQuery(SQL__FIND_PROCEDURES);

            while (resultSet.next()) {
                procedures.add(mapper.mapRow(resultSet));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return procedures;
    }


    private static class ProcedureMapper implements EntityMapper<Procedure> {

        @Override
        public Procedure mapRow(ResultSet rs) {
            try {
                Procedure procedure = new Procedure();
                procedure.setId(rs.getLong(Fields.ENTITY__ID));
                procedure.setTitle(rs.getString(Fields.PROCEDURE__TITLE));
                procedure.setPrice(rs.getInt(Fields.PROCEDURE__PRICE));
                return procedure;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }

}
