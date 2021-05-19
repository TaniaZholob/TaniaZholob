package com.example.TestProject.db.entity;

import java.util.Objects;

/**
 * Procedure entity.
 *
 * @author T.Zholob
 */
public class Procedure extends Entity {
    private String title;
    private int price;
    private CategoryProcedure categoryProcedure;

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

    public CategoryProcedure getCategoryProcedure() {
        return categoryProcedure;
    }

    public void setCategoryProcedure(CategoryProcedure categoryProcedure) {
        this.categoryProcedure = categoryProcedure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Procedure procedure = (Procedure) o;
        return price == procedure.price && title.equals(procedure.title) && categoryProcedure.equals(procedure.categoryProcedure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price, categoryProcedure);
    }

    @Override
    public String toString() {
        return "Procedure{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", categoryProcedure=" + categoryProcedure +
                '}';
    }
}
