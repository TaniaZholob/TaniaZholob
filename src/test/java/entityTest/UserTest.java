package entityTest;

import com.example.TestProject.model.Role;
import com.example.TestProject.model.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    void testUserEntity() {
        User user = new User();
        assertNotNull(user);

        user.setId(24L);
        user.setLogin("taniaZholob@gmail.com");
        user.setPassword("1234");
        user.setFirstName("Tania");
        user.setLastName("Zholob");
        user.setRoleId(1);


        assertEquals(user.getId(), 24L);
        assertEquals(user.getLogin(), "taniaZholob@gmail.com");
        assertEquals(user.getPassword(), "1234");
        assertEquals(user.getFirstName(), "Tania");
        assertEquals(user.getLastName(), "Zholob");
        assertEquals(user.getRoleId(), 1);
        assertEquals(user.toString(), "User{login='taniaZholob@gmail.com', password='1234', firstName='Tania', lastName='Zholob', roleId=1}");
    }

    @Test
    void testRoleOfUser() {
        User user = new User();
        user.setRoleId(1);
        assertEquals(Role.getRole(user), Role.CLIENT);
    }

    @Test
    void EqualsUser() {
        User user1 = new User();
        User user2 = new User();

        user1.setFirstName("Tania");
        user2.setFirstName("Tania");

        user1.setLastName("Zholob");
        user2.setLastName("Zholob");

        user1.setLogin("taniaZholob@gmail.com");
        user2.setLogin("taniaZholob@gmail.com");

        user1.setId(1L);
        user2.setId(1L);

        user1.setRoleId(1);
        user2.setRoleId(1);

        user1.setPassword("tania");
        user2.setPassword("tania");

        assertEquals(user2, user1);
        assertEquals(user2.hashCode(), user1.hashCode());
    }
    @Test
    void testRole(){
     Role role = Role.ADMIN;
     assertEquals(role,Role.ADMIN);
     role = Role.CLIENT;
     assertEquals(role, Role.CLIENT);
     role = Role.MASTER;
     assertEquals(role, Role.MASTER);

    }


}



