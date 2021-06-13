package entityTest;

import com.example.TestProject.model.Payment_Status;
import com.example.TestProject.model.PerformanceStatus;
import com.example.TestProject.model.entity.Order;
import com.example.TestProject.model.entity.Procedure;
import com.example.TestProject.model.entity.TimeSlot;
import com.example.TestProject.model.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class OrderTest {

    @Test
    void OrderTes(){
        Order order = new Order();
        order.setId(8L);
        order.setBill(1);
        User user = new User();
        order.setUser(user);
        Procedure procedure = new Procedure();
        order.setProcedure(procedure);
        order.setPaymentStatus(0);
        order.setPerformanceStatus(0);
        TimeSlot timeSlot = new TimeSlot();
        order.setTimeSlot(timeSlot);

        assertEquals(order.getId(), 8L);
        assertEquals(order.getBill(), 1);
        assertEquals(order.getUser(), user);
        assertEquals(order.getProcedure(), procedure);
        assertEquals(order.getPaymentStatus(), Payment_Status.OPENED);
        assertEquals(order.getPerformanceStatus(), PerformanceStatus.ACTIVE);
        assertEquals(order.getTimeSlot(), timeSlot);
        assertEquals(order.toString(), "Order{bill=1, user=User{login='null', password='null', firstName='null', lastName='null', roleId=0}, procedure=Procedure{title='null', price=0}, paymentStatus=OPENED, performanceStatus=ACTIVE, timeSlot=TimeSlot{master=null}}");
    }

    @Test
    void EqualsUser() {
        Order order = new Order();
        Order order2 = new Order();

        order.setId(8L);
        order.setBill(1);
        User user = new User();
        order.setUser(user);
        Procedure procedure = new Procedure();
        order.setProcedure(procedure);
        order.setPaymentStatus(0);
        order.setPerformanceStatus(0);
        TimeSlot timeSlot = new TimeSlot();
        order.setTimeSlot(timeSlot);

        order2.setId(8L);
        order2.setBill(1);
        order2.setUser(user);
        order2.setProcedure(procedure);
        order2.setPaymentStatus(0);
        order2.setPerformanceStatus(0);
        order2.setTimeSlot(timeSlot);


        assertEquals(order, order2);
        assertEquals(order.hashCode(), order2.hashCode());
    }



}
