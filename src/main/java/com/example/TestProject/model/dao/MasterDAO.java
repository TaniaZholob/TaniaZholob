package com.example.TestProject.model.dao;

import com.example.TestProject.constants.SQLquary;
import com.example.TestProject.model.DBManager;
import com.example.TestProject.model.EntityMapper;
import com.example.TestProject.constants.Fields;
import com.example.TestProject.model.entity.Master;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MasterDAO {

    /**
     * Returns a master with the given identifier.
     *
     * @param id Master identifier.
     * @return Master entity.
     */
    public Master findMaster(Long id) {
        Master master = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnectionInner();
//            connection = DBManager.getInstance().getConnection();
            MasterMapper mapper = new MasterMapper();
            preparedStatement = connection.prepareStatement(SQLquary.SQL__FIND_MASTER_BY_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                master = mapper.mapRow(resultSet);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException throwables) {
            DBManager.getInstance().rollbackAndClose(connection);
            throwables.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return master;
    }

    /**
     * Returns a master with the given identifier.
     *
     * @param name,surname Master identifier.
     * @return Master entity.
     */
    public Master findMaster(String name, String surname) {
        Master master = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnectionInner();
//            connection = DBManager.getInstance().getConnection();
            MasterMapper mapper = new MasterMapper();
            preparedStatement = connection.prepareStatement(SQLquary.SQL__FIND_MASTER_BY_NAME_SURNAME);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                master = mapper.mapRow(resultSet);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException throwables) {
            DBManager.getInstance().rollbackAndClose(connection);
            throwables.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return master;
    }
    /**
     * Returns a list of master with the given procedure.
     *
     * @param procedure title.
     * @return List of master.
     */
    public List<Master> getAllMastersByProcedure(String procedure) {
        List<Master> masters = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
//            connection = DBManager.getInstance().getConnectionInner();
        connection = DBManager.getInstance().getConnection();
            MasterMapper mapper = new MasterMapper();
            preparedStatement = connection.prepareStatement(SQLquary.SQL__FIND_MASTERS_BY_PROCEDURE);
            preparedStatement.setString(1, procedure);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                masters.add(mapper.mapRow(resultSet));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return masters;
    }

    public List<Master> getAllMasters() {
        List<Master> masters = new ArrayList<>();
        Statement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
//            connection = DBManager.getInstance().getConnectionInner();
            connection = DBManager.getInstance().getConnection();
            MasterMapper mapper = new MasterMapper();
            preparedStatement = connection.createStatement();
            resultSet = preparedStatement.executeQuery(SQLquary.SQL__FIND_MASTERS);

            while (resultSet.next()) {
                masters.add(mapper.mapRow(resultSet));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return masters;
    }


    public long findNumberOfMasters() {
        Statement statement;
        ResultSet resultSet;
        Connection connection = null;
        Long l = null;
        try {
            connection = DBManager.getInstance().getConnection();
//            connection = DBManager.getInstance().getConnectionInner();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQLquary.SQL__COUNT_MASTERS);
            if (resultSet.next()) {
                l = resultSet.getLong("id");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return l;
    }

    /**
     * Extracts a user from the result set row.
     */
    private static class MasterMapper implements EntityMapper<Master> {

        @Override
        public Master mapRow(ResultSet rs) {
            try {
                Master master = new Master();
                master.setId(rs.getLong(Fields.ENTITY__ID));
                master.setName(rs.getString(Fields.MASTER__NAME));
                master.setSurname(rs.getString(Fields.MASTER__SURNAME));
                master.setRating(rs.getInt(Fields.MASTER__RATING));
                master.setReviews(new ReviewDAO().getListOfRewiev(master.getId()));

                return master;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }


}
