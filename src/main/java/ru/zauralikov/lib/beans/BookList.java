package ru.zauralikov.lib.beans;

import ru.zauralikov.lib.bd.Database;
import java.sql.*;
import java.util.ArrayList;


public class BookList {
    private ArrayList<Book> bookList = new ArrayList<>();


    private ArrayList<Book> getBooks(String query){

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = Database.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()){
                Book book = new Book();
                book.setId(rs.getLong("id"));
                book.setName(rs.getString("name"));
                book.setGenre(rs.getString("genre"));
                book.setIsbn(rs.getString("isbn"));
                book.setAuthor(rs.getString("author"));
                book.setPageCount(rs.getInt("page_count"));
                book.setPublishDate(rs.getInt("publish_year"));
                book.setPublisher(rs.getString("publisher"));
                book.setImage(rs.getBytes("image"));
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

    public ArrayList<Book> getBooksByGenre(long id){
        return getBooks("select b.id,b.name,b.isbn,b.page_count,b.publish_year, p.name as publisher, a.fio as author, g.name as genre, b.image from book b "
                + "inner join author a on b.author_id=a.id "
                + "inner join genre g on b.genre_id=g.id "
                + "inner join publisher p on b.publisher_id=p.id "
                + "where genre_id=" + id + " order by b.name");
    }

    public ArrayList<Book> getAllBooks(){
        return getBooks("select b.id,b.name,b.isbn,b.page_count,b.publish_year, p.name as publisher, a.fio as author, g.name as genre, b.image from book b "
                + "inner join author a on b.author_id=a.id "
                + "inner join genre g on b.genre_id=g.id "
                + "inner join publisher p on b.publisher_id=p.id "
                + "order by b.name");
    }

}
