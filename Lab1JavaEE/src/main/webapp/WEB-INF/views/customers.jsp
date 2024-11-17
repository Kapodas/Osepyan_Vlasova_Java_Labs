<%--
  Created by IntelliJ IDEA.
  User: osepa
  Date: 14.11.2024
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Список клиентов</title>
</head>
<body>
<h1>Список клиентов</h1>
<table border="1">
  <tr>
    <th>ID</th>
    <th>Имя</th>
    <th>Номер телефона</th>
    <th>Email</th>
  </tr>
  <c:forEach var="customer" items="${customers}">
    <tr>
      <td>${customer.id}</td>
      <td>${customer.name}</td>
      <td>${customer.phoneNumber}</td>
      <td>${customer.email}</td>
    </tr>
  </c:forEach>
</table>
</body>
</html>