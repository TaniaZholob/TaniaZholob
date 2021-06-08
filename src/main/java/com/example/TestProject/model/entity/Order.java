package com.example.TestProject.model.entity;

import com.example.TestProject.model.Payment_Status;
import com.example.TestProject.model.PerformanceStatus;

import java.util.Objects;

public class Order extends Entity {
    private int bill;
    private User user;
    private Procedure procedure;
    private Payment_Status paymentStatus;
    private PerformanceStatus performanceStatus;
    private TimeSlot timeSlot;

    public static void main(String[] args) {
        Order o = new Order();
        o.setPaymentStatus(2);
        System.out.println(o.getPaymentStatus());
    }

    public PerformanceStatus getPerformanceStatus() {
        return performanceStatus;
    }

    public void setPerformanceStatus(int id){
        performanceStatus = PerformanceStatus.values()[id];
        this.performanceStatus = performanceStatus;
    }

    public Payment_Status getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(int paymentStatusid) {
        paymentStatus = Payment_Status.values()[paymentStatusid];

    }

    public int getBill() {
        return bill;
    }

    public void setBill(int bill) {
        this.bill = bill;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Procedure getProcedure() {
        return procedure;
    }

    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }


    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {

        this.timeSlot = timeSlot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return bill == order.bill && user.equals(order.user) && procedure.equals(order.procedure) && paymentStatus == order.paymentStatus && performanceStatus == order.performanceStatus && timeSlot.equals(order.timeSlot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bill, user, procedure, paymentStatus, performanceStatus, timeSlot);
    }

    @Override
    public String toString() {
        return "Order{" +
                "bill=" + bill +
                ", user=" + user +
                ", procedure=" + procedure +
                ", paymentStatus=" + paymentStatus +
                ", performanceStatus=" + performanceStatus +
                ", timeSlot=" + timeSlot +
                '}';
    }
}
