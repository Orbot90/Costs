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
<form action="${pageContext.request.contextPath}/join" method="post">
  <label for="login">Логин:</label>
  <input type="text" size="15" id="login" name="login" />
  <br />
  <label for="password">Пароль:</label>
  <input type="password" size="15" id="password" name="password" />
  <br />
  <label for="pass_again">Повторите пароль:</label>
  <input type="password" size="15" id="pass_again" />
  <br />
  <label for="bal">Стартовый баланс:</label>
  <input type="number" size="10" id="bal" name="balance" />
  <br />
  <label for="email">Email:</label>
  <input type="text" size="15" id="email" name="email">
  <br />
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
  <input type="submit" value="Зарегистрироваться" />
</form>
</body>
</html>
