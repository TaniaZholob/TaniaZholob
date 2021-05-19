package com.example.TestProject.db.dao;

import com.example.TestProject.db.*;
import com.example.TestProject.db.entity.User;

import java.sql.*;


public class UserDAO {
    private static final String SQL__FIND_USER_BY_LOGIN =
            "SELECT * FROM users WHERE login=?";
    private static final String SQL__FIND_USER_BY_ID =
            "SELECT * FROM users WHERE id=?";
    private static final String SQL__UPDATE_USER =
            "UPDATE users SET password=?, first_name=?, last_name=?, locale_name=?" +
                    "	WHERE id=?";
    private static final String SQL__INSERT_USER = "INSERT INTO users VALUES(null, ?,?,?,?,?,?)";


    /**
     * Returns a user with the given identifier.
     *
     * @param id User identifier.
     * @return User entity.
     */
    public User findUser(Long id) {
        User user = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnectionInner();
//            connection = DBManager.getInstance().getConnection();
            UserMapper mapper = new UserMapper();
            preparedStatement = connection.prepareStatement(SQL__FIND_USER_BY_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = mapper.mapRow(resultSet);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException throwables) {
            DBManager.getInstance().rollbackAndClose(connection);
            throwables.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return user;
    }

    /**
     * Returns a user with the given localName.
     *
     * @param login User identifier.
     * @return User entity.
     */


    public User findUser(String login) {
        User user = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnectionInner();
//            connection = DBManager.getInstance().getConnection();
            UserMapper mapper = new UserMapper();
            preparedStatement = connection.prepareStatement(SQL__FIND_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = mapper.mapRow(resultSet);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException throwables) {
            DBManager.getInstance().rollbackAndClose(connection);
            throwables.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return user;

    }

    public void registerUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;
        try {
//            connection = DBManager.getInstance().getConnectionInner();
            connection = DBManager.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL__INSERT_USER);
            int k = 0;
            preparedStatement.setString(++k, user.getLogin());
            preparedStatement.setString(++k, user.getPassword());
            preparedStatement.setString(++k, user.getFirstName());
            preparedStatement.setString(++k, user.getLastName());
            preparedStatement.setString(++k, user.getLocaleName());
            preparedStatement.setInt(++k, Role.CLIENT.ordinal());

            preparedStatement.executeUpdate();
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
    }

    /**
     * Update user.
     *
     * @param user user to update.
     */
    public void updateUser(User user) {
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnectionInner();
//            connection = DBManager.getInstance().getConnection();
            updateUser(connection, user);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(connection);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
    }
    // //////////////////////////////////////////////////////////
    // Entity access methods (for transactions)
    // //////////////////////////////////////////////////////////

    /**
     * Update user.
     *
     * @param user user to update.
     * @throws SQLException
     */
    public void updateUser(Connection connection, User user) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(SQL__UPDATE_USER);
        int k = 1;
        pstmt.setString(k++, user.getPassword());
        pstmt.setString(k++, user.getFirstName());
        pstmt.setString(k++, user.getLastName());
        pstmt.setString(k++, user.getLocaleName());
        pstmt.setLong(k, user.getId());
        pstmt.executeUpdate();
        pstmt.close();
    }

    /**
     * Extracts a user from the result set row.
     */
    private static class UserMapper implements EntityMapper<User> {

        @Override
        public User mapRow(ResultSet rs) {
            try {
                User user = new User();
                user.setId(rs.getLong(Fields.ENTITY__ID));
                user.setLogin(rs.getString(Fields.USER__LOGIN));
                user.setPassword(rs.getString(Fields.USER__PASSWORD));
                user.setFirstName(rs.getString(Fields.USER__FIRST_NAME));
                user.setLastName(rs.getString(Fields.USER__LAST_NAME));
                user.setLocaleName(rs.getString(Fields.USER__LOCALE_NAME));
                user.setRoleId(rs.getInt(Fields.USER__ROLE_ID));
                return user;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    /***Must be Deleted**/
    public static void main(String[] args) {
        UserDAO user = new UserDAO();

        User u = user.findUser(1L);
        u.setLocaleName("tatianaZholob");
        u.setLogin("taniaCamp");
        System.out.println(u);
//        User u =user.findUser("admin");

        user.registerUser(u);

//        System.out.println(Role.CLIENT.ordinal());
//        System.out.println(Role.getRole(u));
    }
}
