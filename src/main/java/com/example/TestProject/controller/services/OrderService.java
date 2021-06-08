package com.example.TestProject.controller.services;

import com.example.TestProject.model.Payment_Status;
import com.example.TestProject.model.PerformanceStatus;
import com.example.TestProject.model.bean.Record;
import com.example.TestProject.model.dao.OrderDAO;
import com.example.TestProject.model.dao.ProcedureDAO;
import com.example.TestProject.model.dao.TimeSlotDAO;
import com.example.TestProject.model.entity.User;

import java.util.List;

public class OrderService {

    public Long getNumberOfRecords() {
        return new OrderDAO().findNumberOfRecords();
    }

    public List<Record> getRecords(int currentPage) {
        List<Record> records;
        int start = currentPage * 2 - 2;
        records = new OrderDAO().getAllRecords(start, 2);
        return records;
    }

    public boolean insertRecord(Long idMaster, String dataTime, User user, String procedure) {
        TimeSlotDAO timeSlotDAO = new TimeSlotDAO();
        Long idTimeSlot = timeSlotDAO.insertTimeSlot(idMaster, dataTime);
        return!new OrderDAO().createNewRecord(user.getId(), new ProcedureDAO().findIdByTitle(procedure), idTimeSlot);
    }

    public boolean changePaymentStatus(Long idPars, Payment_Status ps) {
        OrderDAO orderDAO = new OrderDAO();
        if(orderDAO.getOrderByID(idPars).getPerformanceStatus().equals(PerformanceStatus.CLOSED)){
            ps = Payment_Status.PAID;
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
}
