package ru.zauralikov.lib.beans;

import ru.zauralikov.lib.bd.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AuthorList {
    private ArrayList<Author> authorList = new ArrayList<>();

    private ArrayList<Author> getAuthors(){

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = Database.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery("SELECT fio FROM author ORDER BY fio");
            while (rs.next()){
                Author author = new Author();
                author.setId(rs.getLong("id"));
                author.setName(rs.getString("fio"));
                author.setBirthday(rs.getDate("birthday"));
                authorList.add(author);
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
