package ru.zauralikov.lib.bd;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class Database {

    private static Connection conn;
    private static DataSource ds;
    private static InitialContext ic;

    public static Connection getConnection(){
        try {
            ic = new InitialContext();
            ds = (DataSource)ic.lookup("jdbc/Library");
            if (conn==null) {
                conn = ds.getConnection();
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
