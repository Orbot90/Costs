<%--
  Created by IntelliJ IDEA.
  User: orbot
  Date: 06.07.15
  Time: 23:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
  <h1>Введите свои данные для регистрации</h1>
<form action="#" method="post">
  <label for="login">Логин:</label>
  <input type="text" size="15" id="login" name="login" />
  <br />
  <label for="password">Пароль:</label>
  <input type="password" size="15" id="password" name="password" />
  <br />
  <label for="pass_again">Повторите пароль:</label>
  <input type="password" size="15" id="pass_again" />
  <br />
  <input type="submit" value="Зарегистрироваться" />
</form>
</body>
</html>
