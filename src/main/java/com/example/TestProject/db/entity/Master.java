package com.example.TestProject.db.entity;

import java.util.Objects;
/**
 * Master entity.
 *
 * @author T.Zholob
 *
 */
public class Master extends Entity{
    private String name;
    private String surname;
    private int rating;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Master master = (Master) o;
        return rating == master.rating && name.equals(master.name) && surname.equals(master.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, rating);
    }

    @Override
    public String toString() {
        return "Master{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", rating=" + rating +
                '}';
    }
}
