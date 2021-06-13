package com.example.TestProject.model.dao;

import com.example.TestProject.constants.Fields;
import com.example.TestProject.constants.SQLquary;
import com.example.TestProject.model.DBManager;
import com.example.TestProject.model.EntityMapper;
import com.example.TestProject.model.entity.Master;
import com.example.TestProject.model.entity.Review;
import com.example.TestProject.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {
    public boolean addNewReview(Master master, User user, String text, int rating) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;
        try {
//            connection = DBManager.getInstance().getConnectionInner();
            connection = DBManager.getInstance().getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(SQLquary.SQL__INSERT_REVIEW);
            int k = 0;
            preparedStatement.setLong(++k, master.getId());
            preparedStatement.setLong(++k, user.getId());
            preparedStatement.setString(++k, text);
            preparedStatement.setInt(++k, rating);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(SQLquary.SQL__UPDATE_MASTER_RATING);
            preparedStatement.setLong(1, master.getId());
            preparedStatement.setLong(2, master.getId());
            preparedStatement.executeUpdate();
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            DBManager.getInstance().rollbackAndClose(connection);
            return false;
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return true;
    }

    public List<Review> getListOfRewiev(Long id) {
        List<Review> reviews = new ArrayList<>();
        Review r = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
//            connection = DBManager.getInstance().getConnectionInner();
            connection = DBManager.getInstance().getConnection();
            ReviewMapper mapper = new ReviewMapper();
            preparedStatement = connection.prepareStatement(SQLquary.SQL__FIND_REVIEWS_BY_MASTER_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                reviews.add(mapper.mapRow(resultSet));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return reviews;
    }
    /**
     * Extracts a user from the result set row.
     */
    private static class ReviewMapper implements EntityMapper<Review> {

        @Override
        public Review mapRow(ResultSet rs) {
            try {
                Review review = new Review();
                review.setId(rs.getLong(Fields.ENTITY__ID));
                review.setMasterId(rs.getInt(Fields.MASTER__ID));
                review.setUser(new UserDAO().findUser(rs.getLong(Fields.ORDER__USER_ID)));
                review.setReview(rs.getString(Fields.REVIEW_TEXT));
                review.setRating(rs.getInt(Fields.REVIEW_RATING));


                return review;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }



}
