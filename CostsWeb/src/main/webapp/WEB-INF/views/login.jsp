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
<form method="post">
  <label for="username">Логин:</label>
  <input id="username" name="username" type="text" size="15">
  <br />
  <label for="pass">Пароль:</label>
  <input id="pass" type="password" name="password" size="15">
  <br />
  <label><input type="checkbox" name="remember-me" />Запомнить меня</label>
  <br />
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
  <input type="submit" value="Войти" />
</form>
<br />
<p>Или <a href="${pageContext.request.contextPath}/join">присоединяйтесь</a> к нашей системе</p>
<br />
<p>Также вы можете зайти в систему под <a href="${pageContext.request.contextPath}/test">тестовым пользователем</a> для демонстрациии</p>
</body>
</html>
