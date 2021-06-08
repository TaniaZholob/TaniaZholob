package com.example.TestProject.model.entity;

import java.util.Objects;
/**
 * Review entity.
 *
 * @author T.Zholob
 *
 */
public class Review extends Entity{
    private int masterId;
    private String review;
    private User user;
    private int rating;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
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
        return masterId == review1.masterId &&
                rating == review1.rating &&
                Objects.equals(review, review1.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(masterId, review, rating);
    }

    @Override
    public String toString() {
        return "Review{" +
                "masterId=" + masterId +
                ", review='" + review + '\'' +
                ", rating=" + rating +
                '}';
    }
}
