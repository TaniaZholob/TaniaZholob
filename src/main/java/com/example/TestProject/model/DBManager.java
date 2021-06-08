package com.example.TestProject.model;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DB manager. Works with MySQL DB.
 * Only the required DAO methods are defined!
 *
 * @author T.Zholob
 */
public class DBManager {
    private static final Logger log = Logger.getLogger(DBManager.class);
    // //////////////////////////////////////////////////////////
    // singleton
    // //////////////////////////////////////////////////////////
    private static DBManager instance;

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    /**
     * Returns a DB connection from the Pool Connections. Before using this
     * method you must configure the Date Source and the Connections Pool in your
     * WEB_APP_ROOT/META-INF/context.xml file.
     *
     * @return A DB connection.
     */
    public Connection getConnection() throws SQLException {
        Connection con = null;
        try {

            Context initContext = new InitialContext();

//            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            System.out.println("tania");
            // ST4DB - the name of data source
//            DataSource ds = (DataSource)envContext.lookup("jdbc/TestProject");
            System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                    "org.apache.naming.java.javaURLContextFactory");
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/TestProject");
            con = ds.getConnection();
        } catch (NamingException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            log.error("Cannot obtain a connection from the pool", ex);
        }
        return con;
    }

    private DBManager() {
    }
    // //////////////////////////////////////////////////////////
    // DB util methods
    // //////////////////////////////////////////////////////////

    /**
     * Commits and close the given connection.
     *
     * @param con Connection to be committed and closed.
     */
    public void commitAndClose(Connection con) {
        try {
            if (con.getAutoCommit() == false) {
                con.commit();
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Rollbacks and close the given connection.
     *
     * @param con Connection to be rollbacked and closed.
     */
    public void rollbackAndClose(Connection con) {
        try {
            con.rollback();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * It must be deleted.
     **/
    public Connection getConnectionInner() {
        Connection c = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/beauty_salon?user=root&password=root");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return c;
    }
}
