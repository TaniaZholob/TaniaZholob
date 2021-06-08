package com.example.TestProject.model.entity;

import java.util.Objects;

/**
 * Procedure entity.
 *
 * @author T.Zholob
 */
public class Procedure extends Entity {
    private String title;
    private int price;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Procedure procedure = (Procedure) o;
        return price == procedure.price && title.equals(procedure.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price);
    }

    @Override
    public String toString() {
        return "Procedure{" +
                "title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
