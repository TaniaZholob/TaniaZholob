package com.example.TestProject.db.dao;

import com.example.TestProject.db.*;
import com.example.TestProject.db.entity.CategoryProcedure;

import java.sql.*;


public class CategoryProcedureDAO{

    private static final String SQL__FIND_CATEGORY_BY_ID =
            "SELECT * FROM categories_procedures WHERE id=?";
    /**
     * Returns a categoryProcedure with the given identifier.
     *
     * @param id CategoryProcedure identifier.
     * @return CategoryProcedure entity.
     */
    public CategoryProcedure findCategoryProcedure(Long id) {
        CategoryProcedure categoryProcedure = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnectionInner();
//            connection = DBManager.getInstance().getConnection();
            CategoryProcedureMapper mapper = new CategoryProcedureMapper();
            preparedStatement = connection.prepareStatement(SQL__FIND_CATEGORY_BY_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                categoryProcedure = mapper.mapRow(resultSet);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException throwables) {
            DBManager.getInstance().rollbackAndClose(connection);
            throwables.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return categoryProcedure;
    }
    /**
     * Extracts a categoryProcedure from the result set row.
     */
    private static class CategoryProcedureMapper implements EntityMapper<CategoryProcedure> {

        @Override
        public CategoryProcedure mapRow(ResultSet rs) {
            try {
                CategoryProcedure procedure = new CategoryProcedure();
                procedure.setId(rs.getLong(Fields.ENTITY__ID));
                procedure.setTitle(rs.getString(Fields.PROCEDURE__TITLE));
                return procedure;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
/**must be deleted**/
    public static void main(String[] args) {
        CategoryProcedureDAO c = new CategoryProcedureDAO();
        System.out.println(c.findCategoryProcedure(1L));
    }
}
