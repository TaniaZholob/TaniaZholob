package com.example.TestProject.model.bean;

import java.util.Objects;

public class Record {
    private Long idOfRecord;
    private String emailOfUsers;
    private String nameAndSurnameOfUser;
    private String nameAndSurnameOfMAster;
    private String procedure;
    private String dataTime;
    private String paymentStatus;
    private String performStatus;

    public String getPerformStatus() {
        return performStatus;
    }

    public void setPerformStatus(String performStatus) {
        this.performStatus = performStatus;
    }

    public Long getIdOfRecord() {
        return idOfRecord;
    }

    public void setIdOfRecord(Long idOfRecord) {
        this.idOfRecord = idOfRecord;
    }

    public String getEmailOfUsers() {
        return emailOfUsers;
    }

    public void setEmailOfUsers(String emailOfUsers) {
        this.emailOfUsers = emailOfUsers;
    }

    public String getNameAndSurnameOfUser() {
        return nameAndSurnameOfUser;
    }

    public void setNameAndSurnameOfUser(String name, String surname) {
        nameAndSurnameOfUser = name + " " + surname;
    }

    public String getNameAndSurnameOfMAster() {
        return nameAndSurnameOfMAster;
    }

    public void setNameAndSurnameOfMAster(String name, String surname) {
        nameAndSurnameOfMAster = name + " " + surname;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return Objects.equals(idOfRecord, record.idOfRecord) && Objects.equals(emailOfUsers, record.emailOfUsers) && Objects.equals(nameAndSurnameOfUser, record.nameAndSurnameOfUser) && Objects.equals(nameAndSurnameOfMAster, record.nameAndSurnameOfMAster) && Objects.equals(procedure, record.procedure) && Objects.equals(dataTime, record.dataTime) && paymentStatus == record.paymentStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOfRecord, emailOfUsers, nameAndSurnameOfUser, nameAndSurnameOfMAster, procedure, dataTime, paymentStatus);
    }

    @Override
    public String toString() {
        return "Record{" +
                "idOfRecord=" + idOfRecord +
                ", emailOfUsers='" + emailOfUsers + '\'' +
                ", nameAndSurnameOfUser='" + nameAndSurnameOfUser + '\'' +
                ", nameAndSurnameOfMAster='" + nameAndSurnameOfMAster + '\'' +
                ", procedure='" + procedure + '\'' +
                ", dataTime='" + dataTime + '\'' +
                ", paymentStatus=" + paymentStatus +
                '}';
    }
}
