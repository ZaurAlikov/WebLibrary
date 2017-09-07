package ru.zauralikov.lib.beans;

import ru.zauralikov.lib.bd.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GenreList {
    private ArrayList<Genre> genreList = new ArrayList<>();
    private Connection conn = null;
    private Statement st = null;
    private ResultSet rs = null;

    private ArrayList<Genre> getGenres(){

        try {
            conn = Database.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery("SELECT name FROM genre ORDER BY name");
            while (rs.next()){
                genreList.add(new Genre(rs.getString("name")));
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
        return genreList;
    }

    public ArrayList<Genre> getGenreList(){
        if (!genreList.isEmpty()){
            return genreList;
        } else {
            return getGenres();
        }
    }
}
