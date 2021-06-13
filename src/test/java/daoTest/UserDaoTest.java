package daoTest;

import com.example.TestProject.model.DBManager;
import com.example.TestProject.model.dao.UserDAO;
import com.example.TestProject.model.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class UserDaoTest {
    @BeforeAll
    static void reloadTestDB() throws IOException, SQLException {
        Connection con = DBManager.getInstance().getConnectionInner();

    }

    @Test
    void findUserById(){
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findUser(1L);
        System.out.println("hello");
        System.out.println(user);

    }



}
