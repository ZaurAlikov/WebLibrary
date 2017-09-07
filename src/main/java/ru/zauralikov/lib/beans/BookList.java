package ru.zauralikov.lib.beans;

import ru.zauralikov.lib.bd.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BookList {
    private ArrayList<Book> bookList = new ArrayList<>();
    private Connection conn = null;
    private Statement st = null;
    private ResultSet rs = null;

    private ArrayList<Book> getBooks(){

        try {
            conn = Database.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM book ORDER BY id");
            while (rs.next()){
                Book book = new Book();
                book.setName(rs.getString("name"));
                book.setPageCount(rs.getInt("page_count"));
                book.setIsbn(rs.getString("isbn"));
                book.setPublishDate(rs.getDate("publish_year"));
                bookList.add(book);
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
        return bookList;
    }

    public ArrayList<Book> getBookList(){
        if (!bookList.isEmpty()){
            return bookList;
        } else {
            return getBooks();
        }
    }
}
