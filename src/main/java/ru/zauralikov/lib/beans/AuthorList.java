package ru.zauralikov.lib.beans;

import ru.zauralikov.lib.bd.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AuthorList {
    private ArrayList<Author> authorList = new ArrayList<>();
    private Connection conn = null;
    private Statement st = null;
    private ResultSet rs = null;

    private ArrayList<Author> getAuthors(){

        try {
            conn = Database.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery("SELECT fio FROM author ORDER BY fio");
            while (rs.next()){
                authorList.add(new Author(rs.getString("fio")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (conn != null) conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return authorList;
    }

    public ArrayList<Author> getAuthorList(){
        if (!authorList.isEmpty()){
            return authorList;
        } else {
            return getAuthors();
        }
    }
}
