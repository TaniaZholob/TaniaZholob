package com.example.TestProject.db.dao;

import com.example.TestProject.db.DBManager;
import com.example.TestProject.db.EntityMapper;
import com.example.TestProject.db.Fields;
import com.example.TestProject.db.entity.Procedure;
import com.example.TestProject.db.entity.TimeSlot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProcedureDAO {
    private static final String SQL__FIND_PROCEDURE_BY_ID =
            "SELECT * FROM procedures WHERE id=?";
    /**
     * Returns a user with the given identifier.
     *
     * @param id Procedure identifier.
     * @return Procedure entity.
     */
    public Procedure findProcedure(Long id) {
        Procedure procedure = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnectionInner();
//            connection = DBManager.getInstance().getConnection();
            ProcedureMapper mapper = new ProcedureMapper();
            preparedStatement = connection.prepareStatement(SQL__FIND_PROCEDURE_BY_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                procedure = mapper.mapRow(resultSet);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException throwables) {
            DBManager.getInstance().rollbackAndClose(connection);
            throwables.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return procedure;
    }

    private static class ProcedureMapper implements EntityMapper<Procedure> {

        @Override
        public Procedure mapRow(ResultSet rs) {
            try {
                Procedure procedure = new Procedure();
                procedure.setId(rs.getLong(Fields.ENTITY__ID));
                procedure.setTitle(rs.getString(Fields.PROCEDURE__TITLE));
                procedure.setPrice(rs.getInt(Fields.PROCEDURE__PRICE));
                procedure.setCategoryProcedure(new CategoryProcedureDAO().findCategoryProcedure(rs.getLong(Fields.PROCEDURE__CATEGORY_ID)));
                return procedure;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    public static void main(String[] args) {
        ProcedureDAO p = new ProcedureDAO();
        System.out.println(p.findProcedure(2L));
    }
}
