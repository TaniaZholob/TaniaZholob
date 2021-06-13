package entityTest;

import com.example.TestProject.model.entity.Review;
import com.example.TestProject.model.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReviewTest {
    @Test
    void testReview(){
        Review review = new Review();

        review.setId(1L);
        review.setReview("Good job");
        review.setRating(9);
        User user = new User();
        review.setUser(user);
        review.setMasterId(3);


        assertEquals(review.getId(), 1L);
        assertEquals(review.getReview(), "Good job");
        assertEquals(review.getRating(), 9);
        assertEquals(review.getUser(), user);
        assertEquals(review.getMasterId(), 3);

        assertEquals(review.toString(), "Review{masterId=3, review='Good job', rating=9}");


        Review review2 = new Review();

        review2.setId(1L);
        review2.setReview("Good job");
        review2.setRating(9);
        review2.setUser(user);
        review2.setMasterId(3);

        assertEquals(review,review2);
        assertEquals(review.hashCode(),review2.hashCode());
    }



}
