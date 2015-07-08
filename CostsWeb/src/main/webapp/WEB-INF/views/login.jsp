<%--
  Created by IntelliJ IDEA.
  User: orbot
  Date: 06.07.15
  Time: 22:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добро пожаловать</title>
  <meta charset="UTF-8">
</head>
<body>
<h1>Введите логин и пароль для доступа к приложению.</h1>
<form action="/j_spring_security_check" method="post">
  <label for="username">Логин:</label>
  <input id="username" name="j_username" type="text" size="15">
  <br />
  <label for="pass">Пароль:</label>
  <input id="pass" type="password" name="j_password" size="15">
  <br />
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
  <input type="submit" value="Войти" />
</form>
<br />
<p>Или <a href="${pageContext.request.contextPath}/join">присоединяйтесь</a> к нашей системе</p>
</body>
</html>
