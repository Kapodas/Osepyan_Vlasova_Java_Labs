<%--
  Created by IntelliJ IDEA.
  User: osepa
  Date: 14.11.2024
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Список клиентов</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f4;
      margin: 0;
      padding: 0;
    }
    .container {
      width: 80%;
      margin: 50px auto;
      background-color: #fff;
      padding: 20px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    h1 {
      text-align: center;
      color: #333;
    }
    table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 20px;
    }
    table, th, td {
      border: 1px solid #ddd;
    }
    th, td {
      padding: 10px;
      text-align: left;
    }
    th {
      background-color: #007bff;
      color: white;
    }
    tr:nth-child(even) {
      background-color: #f2f2f2;
    }
    .error {
      color: red;
      text-align: center;
      margin-top: 20px;
    }
    .add-form {
      margin-top: 20px;
    }
    .add-form label {
      display: block;
      margin-top: 10px;
    }
    .add-form input[type="text"], .add-form input[type="email"] {
      width: 100%;
      padding: 10px;
      margin-top: 5px;
      border: 1px solid #ddd;
      border-radius: 5px;
    }
    .add-form input[type="submit"] {
      margin-top: 20px;
      padding: 10px 20px;
      background-color: #28a745;
      color: white;
      border: none;
      border-radius: 5px;
      cursor: pointer;
    }
    .add-form input[type="submit"]:hover {
      background-color: #218838;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>Список клиентов</h1>
  <c:if test="${not empty errorMessage}">
    <p class="error">${errorMessage}</p>
  </c:if>
  <table>
    <tr>
      <th>ID</th>
      <th>Имя</th>
      <th>Номер телефона</th>
      <th>Email</th>
      <th>Действия</th>
    </tr>
    <c:forEach var="customer" items="${customers}">
      <tr>
        <td>${customer.id}</td>
        <td>${customer.name}</td>
        <td>${customer.phoneNumber}</td>
        <td>${customer.email}</td>
        <td>
          <form action="customer-servlet" method="post" style="display:inline;">
            <input type="hidden" name="action" value="delete">
            <input type="hidden" name="id" value="${customer.id}">
            <input type="submit" value="Удалить">
          </form>
        </td>
      </tr>
    </c:forEach>
  </table>

  <h2>Добавить нового клиента</h2>
  <form action="customer-servlet" method="post" class="add-form">
    <input type="hidden" name="action" value="add">
    <label for="name">Имя:</label>
    <input type="text" id="name" name="name" required>
    <label for="phoneNumber">Номер телефона:</label>
    <input type="text" id="phoneNumber" name="phoneNumber" required>
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required>
    <input type="submit" value="Добавить">
  </form>
</div>
</body>
</html>