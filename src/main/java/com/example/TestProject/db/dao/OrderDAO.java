package com.example.TestProject.db.dao;

import com.example.TestProject.db.DBManager;
import com.example.TestProject.db.EntityMapper;
import com.example.TestProject.db.Fields;
import com.example.TestProject.db.entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object for Order entity .
 */
public class OrderDAO {
    private static final String SQL__FIND_ALL_ORDERS =
            "SELECT * FROM orders";
    private static final String SQL__FIND_ORDERS_BY_STATUS =
            "SELECT * FROM orders WHERE payment_status_id=?";
    /**
     * Returns all orders.
     *
     * @return List of order entities.
     */
    public List<Order> findOrders() {
        List<Order> ordersList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnectionInner();
//            connection = DBManager.getInstance().getConnection();
            OrderMapper mapper = new OrderMapper();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(SQL__FIND_ALL_ORDERS);
            while (rs.next())
                ordersList.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return ordersList;
    }
    /**
     * Returns orders with the given status.
     *
     * @param paymentStatusId
     *            Status identifier.
     * @return List of order entities.
     */
    public List<Order> findOrders(int paymentStatusId) {
        List<Order> ordersList = new ArrayList<Order>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnectionInner();
//            connection = DBManager.getInstance().getConnection();
            OrderMapper mapper = new OrderMapper();
            pstmt = connection.prepareStatement(SQL__FIND_ORDERS_BY_STATUS);
            pstmt.setInt(1, paymentStatusId);
            rs = pstmt.executeQuery();
            while (rs.next())
                ordersList.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return ordersList;
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
                order.setProcedure(new ProcedureDAO().findProcedure(rs.getLong(Fields.ORDER__PROCEDURE_ID)));
                order.setPaymentStatus(rs.getInt(Fields.ORDER__PAYMENT_STATUS_ID));
                order.setPerformanceStatus(rs.getInt(Fields.ORDER__PERFORMANCE_STATUS_ID));
                order.setTimeSlot(new TimeSlotDAO().findTimeSlot(rs.getLong(Fields.ORDER__TIME_SLOT_ID)));
                return order;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    public static void main(String[] args) {
        OrderDAO o = new OrderDAO();
        System.out.println(o.findOrders(0));
    }
}
