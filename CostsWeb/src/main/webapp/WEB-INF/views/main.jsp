<%--
  Created by IntelliJ IDEA.
  User: orbot
  Date: 06.07.15
  Time: 23:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Главная страница</title>
  <script>
    function showForm() {
      document.getElementById("newcostform").style.display = "block";
    }
  </script>

</head>
<body>
  Здесь вы можете посмотреть историю своего дохода и расходов, либо добавить <a href="#" onclick="showForm(); return false;" >новую запись</a>.

<div id="newcostform" style="display: none;">
  <form action="#" method="post">
    <label for="num">Сумма:</label><br />
    <input type="number" id="num" name="value" />
    <br />
    <label for="desc">Описание:</label>
    <br />
    <textarea rows="10" cols="15" id="desc" name="description"></textarea>
    <br />
    <label for="tags">Тэги:</label><br />
    <input type="text" size="40" name="tags" id="tags" />
    <br />
    <label><input type="radio" name="type" value="0" checked>Расход</label><br />
    <label><input type="radio" name="type" value="1">Доход</label><br />
    <input type="submit" value="Отправить">
  </form>
</div>
</body>
</html>
