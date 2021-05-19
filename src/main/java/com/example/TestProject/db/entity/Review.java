package com.example.TestProject.db.entity;

import java.util.Objects;
/**
 * Review entity.
 *
 * @author T.Zholob
 *
 */
public class Review extends Entity{
    private Master master;
    private User user;
    private String review;
    private int rating;

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
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
        Review review1 = (Review) o;
        return rating == review1.rating && master.equals(review1.master) && Objects.equals(user, review1.user) && review.equals(review1.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(master, user, review, rating);
    }

    @Override
    public String toString() {
        return "Review{" +
                "master=" + master +
                ", user=" + user +
                ", Review='" + review + '\'' +
                ", rating=" + rating +
                '}';
    }
}
