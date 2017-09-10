<%@ page import="ru.zauralikov.lib.beans.Book" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ru.zauralikov.lib.enums.SearchType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../WEB-INF/jspf/left_menu.jspf"%>

<%request.setCharacterEncoding("UTF-8");
    long genreId = -1;
%>

<jsp:useBean id="bookList" class="ru.zauralikov.lib.beans.BookList" scope="page"/>

<div class="book_list">

    <%
        ArrayList<Book> list = null;
        if (request.getParameter("genre_id") != null){
            genreId = Long.valueOf(request.getParameter("genre_id"));
            if (genreId == 0){
                list = bookList.getAllBooks();
            } else {
                list = bookList.getBooksByGenre(genreId);
            }
        } else if (request.getParameter("search_string") != null){
            String searchStr = request.getParameter("search_string");
            SearchType searchType = SearchType.TITLE;
            if (request.getParameter("search_option").equals("Автор")){
                searchType = SearchType.AUTHOR;
            }
            if (searchStr != null && searchStr.trim() != ""){
                list = bookList.getBooksBySearch(searchStr, searchType);
            }
        }
    %>

    <h5 style="text-align: left">
        <%--${param.name}--%>
        Найдено книг: <%=list.size()%>
    </h5>

    <%
        session.setAttribute("currentBookList", list);
        for (Book book : list){
    %>

    <div class="book_main">
        <div class="book_title">
            <p class="book_title"><%=book.getName()%></p>
        </div>
        <div class="book_img">
            <img src="<%=request.getContextPath()%>/ShowImage?index=<%=list.indexOf(book)%>" height="250" width="190">
        </div>
        <div class="book_disc">
            <br><strong>ISBN: </strong><%=book.getIsbn()%>
            <br><strong>Издательство: </strong><%=book.getPublisher()%>
            <br><strong>Количество страниц: </strong><%=book.getPageCount()%>
            <br><strong>Год издания: </strong><%=book.getPublishDate()%>
            <br><strong>Автор: </strong><%=book.getAuthor()%>
            <p style="margin: 10px;"><a style="text-decoration: underline" href="<%=request.getContextPath()%>/PdfReader?index=<%=list.indexOf(book)%>">Читать</a> </p>
        </div>
    </div>
    <%}%>

</div>


