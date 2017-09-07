<%@ page import="ru.zauralikov.lib.bd.Database" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 005 05.09.17
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Онлайн библиотека</title>
    <link href="../css/main_style.css" rel="stylesheet" type="text/css">
</head>
<body>
    <%--<% request.setCharacterEncoding("UTF-8"); %>--%>
    <div class="main">
        <div class="header">
            <div>
                <img class="logo" src="../images/logo.png" alt="Логотип" name="logo">
            </div> <%-- close logo div --%>
            <div class="discr">
                <h1>Ваша онлайн библиотека</h1>
            </div> <%-- close discr div --%>
        </div> <%-- close header div --%>
        <div class="search_div">
            <form class="search" name="search" action="" method="post">
                <input type="text" name="search" value="" size="100">
                <input class="btn" type="submit" value="Поиск">
                <select name="search_option">
                    <option>Название</option>
                    <option>Автор</option>
                </select>
            </form>
        </div> <%-- close search div --%>
        <div class="sideBar1">
            <h4>Список авторов:</h4>
            <ul class="nav">
                <jsp:useBean id="authorList" class="ru.zauralikov.lib.beans.AuthorList" scope="session"/>
                <c:forEach var="author" items="${authorList.authorList}">
                    <li><a href="#">${author.name}</a></li>
                </c:forEach>
            </ul>
        </div> <%-- close content_left div --%>
        <div class="content">
            <h1>&nbsp;</h1>
            <p>&nbsp;</p>
        </div>
    </div> <%-- close main div --%>
    <div style="clear:both"></div>
</body>
</html>
