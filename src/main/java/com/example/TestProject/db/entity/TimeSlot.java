package com.example.TestProject.db.entity;

import java.util.Objects;
/**
 * TimeSlot entity.
 *
 * @author T.Zholob
 *
 */
public class TimeSlot extends Entity{
    private String timeSlot;
    private Master master;

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeSlot timeSlot1 = (TimeSlot) o;
        return timeSlot.equals(timeSlot1.timeSlot) && master.equals(timeSlot1.master);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeSlot, master);
    }

    @Override
    public String toString() {
        return "TimeSlot{" +
                "timeSlot='" + timeSlot + '\'' +
                ", master=" + master +
                '}';
    }
}
