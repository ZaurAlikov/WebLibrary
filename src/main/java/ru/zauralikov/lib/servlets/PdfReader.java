package ru.zauralikov.lib.servlets;

import ru.zauralikov.lib.beans.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class PdfReader extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/pdf");
        try(OutputStream out = response.getOutputStream()) {
            int index = Integer.valueOf(request.getParameter("index"));
            ArrayList<Book> list = (ArrayList<Book>) request.getSession(false).getAttribute("currentBookList");
            Book book = list.get(index);
            book.fillPdfContent();
            response.setContentLength(book.getContent().length);
            out.write(book.getContent());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
