package entityTest;

import com.example.TestProject.model.entity.Master;
import com.example.TestProject.model.entity.Review;
import com.example.TestProject.model.entity.TimeSlot;
import com.example.TestProject.model.entity.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MasterTest {

    @Test
    public void testMasterTest() {
        Master master = new Master();

        assertNotNull(master);
        List<Review> reviews = new ArrayList<>();
        Review review = new Review();
        review.setUser(new User());
        review.setRating(9);
        review.setReview("Good job");
        Review review2 = new Review();
        review2.setUser(new User());
        review2.setRating(9);
        review2.setReview("Good job1");
        reviews.add(review);
        reviews.add(review2);

        master.setId(10L);
        master.setName("Tania");
        master.setSurname("Zholob");
        master.setRating(9);
        master.setReviews(reviews);


        assertEquals(master.getId(), 10);
        assertEquals(master.getName(), "Tania");
        assertEquals(master.getSurname(), "Zholob");
        assertEquals(master.getRating(), 9);
        assertEquals(master.getReviews(), reviews);
        assertEquals(master.toString(), "Master{name='Tania', surname='Zholob', rating=9, reviews=[Review{masterId=0, review='Good job', rating=9},"
                +" Review{masterId=0, review='Good job1', rating=9}]}");
    }


    @Test
    void EqualsMaster() {
        Master master1 = new Master();
        Master master2 = new Master();

        master1.setId(1L);
        master2.setId(1L);

        List<Review> reviews = new ArrayList<>();
        Review review = new Review();
        review.setUser(new User());
        review.setRating(9);
        review.setReview("Good job");
        Review review2 = new Review();
        review2.setUser(new User());
        review2.setRating(9);
        review2.setReview("Good job1");
        reviews.add(review);
        reviews.add(review2);

        master1.setId(10L);
        master1.setName("Tania");
        master1.setSurname("Zholob");
        master1.setRating(9);
        master1.setReviews(reviews);


        master2.setId(10L);
        master2.setName("Tania");
        master2.setSurname("Zholob");
        master2.setRating(9);
        master2.setReviews(reviews);



        assertEquals(master2, master1);
        assertEquals(master2.hashCode(), master1.hashCode());
    }

    @Test
    void equalsTimeSlot(){
        TimeSlot timeSlot = new TimeSlot();
        TimeSlot timeSlot2 = new TimeSlot();
        Master master = new Master();
        timeSlot.setId(1L);
        timeSlot.setMaster(master);
        assertEquals(timeSlot.getMaster(),master);
        assertEquals(timeSlot.getId(),1L);
        assertEquals(timeSlot.toString(), "TimeSlot{master=Master{name='null', surname='null', rating=0, reviews=null}}");



        timeSlot2.setId(1L);
        timeSlot2.setMaster(master);

        assertEquals(timeSlot,timeSlot2);
        assertEquals(timeSlot.hashCode(),timeSlot2.hashCode());

    }


}
