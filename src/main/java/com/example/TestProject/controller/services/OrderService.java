package com.example.TestProject.controller.services;

import com.example.TestProject.model.Payment_Status;
import com.example.TestProject.model.PerformanceStatus;
import com.example.TestProject.model.bean.Record;
import com.example.TestProject.model.dao.OrderDAO;
import com.example.TestProject.model.dao.ProcedureDAO;
import com.example.TestProject.model.dao.TimeSlotDAO;
import com.example.TestProject.model.entity.Order;
import com.example.TestProject.model.entity.User;

import java.util.List;

public class OrderService {

//    public Long getNumberOfRecords() {
//        return new OrderDAO().findNumberOfRecords();
//    }


    public int getNumberOfPages() {
        long nOfRecords = new OrderDAO().findNumberOfRecords();
        return (int) Math.ceil((double) nOfRecords / 2.0);
    }



    public List<Record> getRecords(int currentPage) {
        List<Record> records;
        int start = currentPage * 2 - 2;
        records = new OrderDAO().getAllRecords(start, 2);
        return records;
    }

    public boolean insertRecord(Long idMaster, String dataTime, User user, String procedure) {
        return!new OrderDAO().createNewRecord(user.getId(), new ProcedureDAO().findIdByTitle(procedure), idMaster, dataTime);
    }

    public boolean changePaymentStatus(Long idPars, Payment_Status ps,User user) {
        OrderDAO orderDAO = new OrderDAO();
        Order order = orderDAO.getOrderByID(idPars);
        if(order.getPerformanceStatus().equals(PerformanceStatus.CLOSED)&&order.getPaymentStatus().equals(Payment_Status.CONFIRMED)){
            System.out.println("Status perform equals CLOSED"+orderDAO.getOrderByID(idPars).getPerformanceStatus());
            ps = Payment_Status.PAID;
            System.out.println("payment stat");
            new SenderService().sendingLetter(user);
        }
        return !orderDAO.updatePaymentStatus(idPars, ps);
    }

    public List<Record> getAllRecordsOfUser(User user) {
        return new OrderDAO().getAllRecordsByUser(user);
    }

    public boolean changeTimeOfOrder(Long id, String time) {
        OrderDAO orderDAO = new OrderDAO();
        return !orderDAO.updateTimeOfOrder(orderDAO.getIdOfTimeSlot(id), time);
    }

    public boolean deleteRecord(Long id) {
        OrderDAO orderDAO = new OrderDAO();
        return !orderDAO.deleteOrder(id);
    }

    public List<Record> getRecordsOfMaster(User user) {
        return new OrderDAO().getAllRecordsOfMaster(user.getFirstName(), user.getLastName());
    }

    public boolean changePerformStatus(Long id) {
        OrderDAO orderDAO = new OrderDAO();

        if(orderDAO.getOrderByID(id).getPaymentStatus().equals(Payment_Status.PAID)){
            orderDAO.updatePaymentStatus(id, Payment_Status.PAID);
        }
        return !orderDAO.changePerformStatus(id);
    }

    public User getUserOfRecord(Long idPars) {
        return new OrderDAO().getOrderByID(idPars).getUser();
    }
}
