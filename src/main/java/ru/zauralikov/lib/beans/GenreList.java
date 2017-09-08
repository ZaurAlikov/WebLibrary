package ru.zauralikov.lib.beans;

import ru.zauralikov.lib.bd.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GenreList {
    private ArrayList<Genre> genreList = new ArrayList<>();

    private ArrayList<Genre> getGenres(){

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = Database.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM genre ORDER BY name");
            while (rs.next()){
                Genre genre = new Genre();
                genre.setId(rs.getLong("id"));
                genre.setGenre(rs.getString("name"));
                genreList.add(genre);
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
