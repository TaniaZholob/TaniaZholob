package com.example.TestProject.db.dao;

import com.example.TestProject.db.DBManager;
import com.example.TestProject.db.EntityMapper;
import com.example.TestProject.db.Fields;
import com.example.TestProject.db.entity.Master;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MasterDAO {

    private static final String SQL__FIND_MASTER_BY_ID =
            "SELECT * FROM masters WHERE id=?";
    private static final String SQL__FIND_MASTER_BY_ID_LIMIT =
            "SELECT * FROM masters limit ?,?";

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
            preparedStatement = connection.prepareStatement(SQL__FIND_MASTER_BY_ID);
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


    public List<Master> getMasters(int start) {
        System.out.println("getMasters");
        int total = 4;
        List<Master> masters = new ArrayList<>();
        Master master = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
//            connection = DBManager.getInstance().getConnectionInner();
          connection = DBManager.getInstance().getConnection();
            MasterMapper mapper = new MasterMapper();
            preparedStatement = connection.prepareStatement(SQL__FIND_MASTER_BY_ID_LIMIT);
            preparedStatement.setInt(1,start-1);
            preparedStatement.setInt(2,total);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
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

                return master;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    /***Must be Deleted**/
    public static void main(String[] args) {
        MasterDAO m = new MasterDAO();
        System.out.println(m.getMasters(1));

    }
}
