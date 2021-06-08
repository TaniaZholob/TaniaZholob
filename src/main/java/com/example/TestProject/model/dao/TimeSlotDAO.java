package com.example.TestProject.model.dao;

import com.example.TestProject.constants.SQLquary;
import com.example.TestProject.model.DBManager;
import com.example.TestProject.model.EntityMapper;
import com.example.TestProject.constants.Fields;
import com.example.TestProject.model.entity.TimeSlot;

import java.sql.*;

public class TimeSlotDAO {


    /**
     * Returns a timeSlot with the given identifier.
     *
     * @param id TimeSlot identifier.
     * @return TimeSlot entity.
     */
    public TimeSlot findTimeSlot(Long id) {
        TimeSlot timeSlot = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnectionInner();
//            connection = DBManager.getInstance().getConnection();
            TimeSlotMapper mapper = new TimeSlotMapper();
            preparedStatement = connection.prepareStatement(SQLquary.SQL__FIND_TIMESLOT_BY_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                timeSlot = mapper.mapRow(resultSet);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException throwables) {
            DBManager.getInstance().rollbackAndClose(connection);
            throwables.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return timeSlot;
    }


    public Long insertTimeSlot(Long masterId, String dataTime) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;
        Long id = null;
        try {
//            connection = DBManager.getInstance().getConnectionInner();
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQLquary.SQL__INSERT_TIME_SLOT, Statement.RETURN_GENERATED_KEYS);
            int k = 0;
            preparedStatement.setString(++k, dataTime);
            preparedStatement.setLong(++k, masterId);
            preparedStatement.executeUpdate();
            resultSet =preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException throwables) {
            return id;
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return id;
    }

    public Long getId(Long idMaster, String dataTime) {
        Long id = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
//            connection = DBManager.getInstance().getConnectionInner();
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQLquary.SQL__FIND_TIMESLOT_BY_DATES);
            preparedStatement.setLong(1, idMaster);
            preparedStatement.setString(2, dataTime);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getLong("id");
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException throwables) {
            DBManager.getInstance().rollbackAndClose(connection);
            throwables.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return id;
    }

    /**
     * Extracts a timeslot from the result set row.
     */
    private static class TimeSlotMapper implements EntityMapper<TimeSlot> {

        @Override
        public TimeSlot mapRow(ResultSet rs) {
            try {
                TimeSlot timeSlot = new TimeSlot();
                timeSlot.setId(rs.getLong(Fields.ENTITY__ID));
//                timeSlot.setTimeSlot(rs.getString(Fields.TIMESLOT__DATE_TIME));
                timeSlot.setMaster(new MasterDAO().findMaster(rs.getLong(Fields.TIMESLOT__MASTER_ID)));
                return timeSlot;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }


    public static void main(String[] args) {
        TimeSlotDAO t = new TimeSlotDAO();
        System.out.println("Id of time slots "+t.getId(2L, "2021-05-29 10:00:00"));
//        System.out.println(t.insertTimeSlot(2L, "2020-01-01 10:10:10"));
    }
}
