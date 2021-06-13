package com.example.TestProject.model.dao;

import com.example.TestProject.constants.Fields;
import com.example.TestProject.model.*;
import com.example.TestProject.model.entity.User;

import java.sql.*;
import java.util.Optional;

import static com.example.TestProject.constants.SQLquary.*;


public class UserDAO {

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


    public Optional<User> findUser(String login, String password) {//optional
        User user = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
//            connection = DBManager.getInstance().getConnectionInner();
            connection = DBManager.getInstance().getConnection();
            UserMapper mapper = new UserMapper();
            preparedStatement = connection.prepareStatement(SQL__FIND_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
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
        return Optional.ofNullable(user);

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
                user.setRoleId(rs.getInt(Fields.USER__ROLE_ID));
                return user;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }

}
