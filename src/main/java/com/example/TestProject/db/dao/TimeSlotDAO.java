package com.example.TestProject.db.dao;

import com.example.TestProject.db.DBManager;
import com.example.TestProject.db.EntityMapper;
import com.example.TestProject.db.Fields;
import com.example.TestProject.db.entity.TimeSlot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TimeSlotDAO {
    private static final String SQL__FIND_TIMESLOT_BY_ID =
            "SELECT * FROM time_slots WHERE id=?";

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
            preparedStatement = connection.prepareStatement(SQL__FIND_TIMESLOT_BY_ID);
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

    /**
     * Extracts a timeslot from the result set row.
     */
    private static class TimeSlotMapper implements EntityMapper<TimeSlot> {

        @Override
        public TimeSlot mapRow(ResultSet rs) {
            try {
                TimeSlot timeSlot = new TimeSlot();
                timeSlot.setId(rs.getLong(Fields.ENTITY__ID));
                timeSlot.setTimeSlot(rs.getString(Fields.TIMESLOT__DATE_TIME));
                timeSlot.setMaster(new MasterDAO().findMaster(rs.getLong(Fields.TIMESLOT__MASTER_ID)));
                return timeSlot;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    public static void main(String[] args) {
        TimeSlotDAO t = new TimeSlotDAO();
        System.out.println(t.findTimeSlot(1L));
    }
}
