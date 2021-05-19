package com.example.TestProject.db.entity;

import com.example.TestProject.db.DBManager;
import com.example.TestProject.db.Payment_Status;
import com.example.TestProject.db.Role;

import java.sql.*;

public class testDBConnection {
    public static void main(String[] args) throws SQLException {
//        try (Connection d = DriverManager.getConnection("jdbc:mysql://localhost:3306/java?user=root&password=root");
//             Statement s = d.createStatement();
//             ResultSet r = s.executeQuery("SELECT * FROM roles");){
//            while (r.next()) {
//                System.out.println(r.getInt("id") + ", " + r.getString("role"));
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }

        DBManager b = DBManager.getInstance();
        Connection d = b.getConnection();
        Statement s = d.createStatement();
        ResultSet r = s.executeQuery("SELECT * FROM roles");
        while (r.next()) {
            System.out.println(r.getInt("id") + ", " + r.getString("role"));
        }

//        System.out.println(Payment_Status.values()[0]);
        b.commitAndClose(d);

    }

}
