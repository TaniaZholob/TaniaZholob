package com.example.TestProject.model.dao;

import com.example.TestProject.constants.SQLquary;
import com.example.TestProject.model.DBManager;
import com.example.TestProject.model.EntityMapper;
import com.example.TestProject.constants.Fields;
import com.example.TestProject.model.Payment_Status;
import com.example.TestProject.model.bean.Record;
import com.example.TestProject.model.entity.Order;
import com.example.TestProject.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object for Order entity .
 */
public class OrderDAO {

    public long findNumberOfRecords() {
        Statement statement;
        ResultSet resultSet;
        Connection connection = null;
        Long l = null;
        try {
            connection = DBManager.getInstance().getConnection();
//            connection = DBManager.getInstance().getConnectionInner();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQLquary.SQL__COUNT_RECORDS);
            if (resultSet.next()) {
                l = resultSet.getLong("id");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return l;
    }


    public List<Record> getAllRecords(int start, int end) {
        List<Record> records = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
//            connection = DBManager.getInstance().getConnectionInner();
            connection = DBManager.getInstance().getConnection();
            RecordMapper mapper = new RecordMapper();
            pstmt = connection.prepareStatement(SQLquary.SQL__FIND_ALL_RECORDS);
            pstmt.setInt(1, start);
            pstmt.setInt(2, end);

            rs = pstmt.executeQuery();
            while (rs.next())
                records.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }


        return records;
    }

    public boolean updatePaymentStatus(Long idPars, Payment_Status ps) {
        PreparedStatement pstmt;
        Connection connection = null;
        try {
//            connection = DBManager.getInstance().getConnectionInner();
            connection = DBManager.getInstance().getConnection();
            pstmt = connection.prepareStatement(SQLquary.SQL__UPDATE_PAYMENT_STATUS);
            pstmt.setLong(1, ps.ordinal() + 1);
            pstmt.setLong(2, idPars);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            ex.printStackTrace();
            return false;
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return true;
    }

    public List<Record> getAllRecordsByUser(User user) {

        List<Record> records = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
//            connection = DBManager.getInstance().getConnectionInner();
            connection = DBManager.getInstance().getConnection();
            RecordMapper mapper = new RecordMapper();
            pstmt = connection.prepareStatement(SQLquary.SQL__FIND_ALL_RECORDS_BY_USER);
            pstmt.setLong(1, user.getId());
            rs = pstmt.executeQuery();
            while (rs.next())
                records.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }


        return records;
    }

    public boolean updateTimeOfOrder(Long id, String time) {
        PreparedStatement pstmt;
        Connection connection = null;
        try {
//            connection = DBManager.getInstance().getConnectionInner();
            connection = DBManager.getInstance().getConnection();
            pstmt = connection.prepareStatement(SQLquary.SQL__UPDATE_TIME_IN_TIMESLOT);
            pstmt.setString(1, time);
            pstmt.setLong(2, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            ex.printStackTrace();
            return false;
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return true;
    }

    public Long getIdOfTimeSlot(Long id) {
        Long idOfTimeSlot = null;
        PreparedStatement pstmt;
        Connection connection = null;
        ResultSet result;
        try {
//            connection = DBManager.getInstance().getConnectionInner();
            connection = DBManager.getInstance().getConnection();
            pstmt = connection.prepareStatement(SQLquary.SQL__SELECT_ID_OF_TIME_SLOT_BY_ID_OF_ORDER);
            pstmt.setLong(1, id);
            result = pstmt.executeQuery();
            if (result.next()) {
                idOfTimeSlot = result.getLong(Fields.ORDER__TIME_SLOT_ID);
            }
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            ex.printStackTrace();
            return idOfTimeSlot;
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return idOfTimeSlot;

    }

    public boolean deleteOrder(Long id) {
        PreparedStatement pstmt;
        Connection connection = null;
        try {
//            connection = DBManager.getInstance().getConnectionInner();
            connection = DBManager.getInstance().getConnection();
            pstmt = connection.prepareStatement(SQLquary.SQL__DELETE_RECORD);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            ex.printStackTrace();
            return false;
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return true;
    }

    public List<Record> getAllRecordsOfMaster(String firstName, String lastName) {
        List<Record> records = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
//            connection = DBManager.getInstance().getConnectionInner();
            connection = DBManager.getInstance().getConnection();
            RecordMapper mapper = new RecordMapper();
            pstmt = connection.prepareStatement(SQLquary.SQL__FIND_ALL_MASTER_RECORDS);
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            rs = pstmt.executeQuery();
            while (rs.next())
                records.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return records;
    }

    public boolean changePerformStatus(Long id) {
        PreparedStatement pstmt;
        Connection connection = null;
        try {
//            connection = DBManager.getInstance().getConnectionInner();
            connection = DBManager.getInstance().getConnection();
            pstmt = connection.prepareStatement(SQLquary.SQL__CHANGE_PERFORM_STATUS);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            ex.printStackTrace();
            return false;
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return true;
    }

    public Order getOrderByID(Long id) {
        Order order = null;
        PreparedStatement pstmt;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = DBManager.getInstance().getConnectionInner();
//            connection = DBManager.getInstance().getConnection();
            OrderMapper orderMapper = new OrderMapper();
            pstmt = connection.prepareStatement(SQLquary.SQL__GET_ORDER_BY_ID);
            pstmt.setLong(1, id);
            resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                order = orderMapper.mapRow(resultSet);
            }
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            ex.printStackTrace();
            return order;
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return order;
    }


    /**
     * Extracts order from the result set row.
     */
    private static class RecordMapper implements EntityMapper<Record> {

        @Override
        public Record mapRow(ResultSet rs) {
            try {
                Record record = new Record();
                record.setIdOfRecord(rs.getLong(Fields.ENTITY__ID));
                record.setEmailOfUsers(rs.getString(Fields.USER__LOGIN));
                record.setNameAndSurnameOfUser(rs.getString(Fields.USER__FIRST_NAME), rs.getString(Fields.USER__LAST_NAME));
                record.setNameAndSurnameOfMAster(rs.getString(Fields.Record__NAME_OF_MASTER), rs.getString(Fields.Record__SURNAME_OF_MASTER));
                record.setDataTime(rs.getString(Fields.Record__DATE_TIME));
                record.setProcedure(rs.getString(Fields.Record__SERVICE));
                record.setPaymentStatus(rs.getString(Fields.Record__PAUMENTSTATUS));
                record.setPerformStatus(rs.getString(Fields.Record__PERFORM_STATUS));
                return record;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }


    /**
     * Extracts order from the result set row.
     */
    private static class OrderMapper implements EntityMapper<Order> {

        @Override
        public Order mapRow(ResultSet rs) {
            try {
                Order order = new Order();
                order.setId(rs.getLong(Fields.ENTITY__ID));
                order.setBill(rs.getInt(Fields.ORDER__BILL));
                order.setUser(new UserDAO().findUser(rs.getLong(Fields.ORDER__USER_ID)));
                order.setProcedure(new ProcedureDAO().findProcedurebyID(rs.getLong(Fields.ORDER__PROCEDURE_ID)));
                order.setPaymentStatus(rs.getInt(Fields.ORDER__PAYMENT_STATUS_ID));
                order.setPerformanceStatus(rs.getInt(Fields.ORDER__PERFORMANCE_STATUS_ID));
                order.setTimeSlot(new TimeSlotDAO().findTimeSlot(rs.getLong(Fields.ORDER__TIME_SLOT_ID)));
                return order;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }


    public boolean createNewRecord(Long userId, Long procedureId, Long timeSlotId) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;
        try {
//            connection = DBManager.getInstance().getConnectionInner();
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQLquary.SQL__INSERT_Record);
            int k = 0;
            preparedStatement.setLong(++k, userId);
            preparedStatement.setLong(++k, procedureId);
            preparedStatement.setLong(++k, timeSlotId);

            preparedStatement.executeUpdate();
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return true;
    }

    public static void main(String[] args) {
        OrderDAO o = new OrderDAO();
//        User u = new User();
//        u.setId(2L);

        System.out.println(o.getOrderByID(7L));
    }
}
