<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 005 05.09.17
  Time: 18:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"   language="java" %>
<html>
  <head>
    <title>Онлайн библиотека</title>
    <link href="css/index_style.css" rel="stylesheet" type="text/css">
  </head>
  <body>
    <div class="main">
        <p class="image"><img src="images/book.ico" width="100" height="100" alt="book"></p>
        <h3>Онлайн библиотека</h3>
        <p>Добро пожаловать в онлайн библиотеку, где вы сможете найти любую книгу на ваш вкус.
        Здесь доступны функции поиска, просмотра, сортировки и многие другие.</p>
        <div class="log_div">
          <h5>Для входа введите свои данные</h5>
          <form class="login_form" name="username" action="pages/main.jsp" method="post">
            Имя: <input type="text" name="username" value="" size="20">
            <input type="submit" value="Войти">
          </form>
        </div>
    </div>
  </body>
</html>
