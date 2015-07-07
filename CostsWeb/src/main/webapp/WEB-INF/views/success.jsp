<%--
  Created by IntelliJ IDEA.
  User: orbot
  Date: 06.07.15
  Time: 23:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вы зашли</title>
</head>
<body>
<p>Поздравляю! Вы успешно залогинились</p>
<script language="JavaScript" type="text/javascript">
  function reDirect(){
    document.location.href="${pageContext.request.contextPath}/main";
  }
  setTimeout( 'reDirect()', 1000 );
</script>
</body>
</html>
