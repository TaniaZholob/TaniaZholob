package com.example.TestProject.model.entity;

import java.util.List;
import java.util.Objects;
/**
 * TimeSlot entity.
 *
 * @author T.Zholob
 *
 */
public class TimeSlot extends Entity{
//    List<Times> times;
    private Master master;

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        TimeSlot timeSlot = (TimeSlot) o;
//        return times.equals(timeSlot.times) && master.equals(timeSlot.master);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(times, master);
//    }
//
//    @Override
//    public String toString() {
//        return "TimeSlot{" +
//                "times=" + times +
//                ", master=" + master +
//                '}';
//    }
}
