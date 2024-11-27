<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Список категорий</title>
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
    .add-form input[type="text"] {
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
  <h1>Список категорий</h1>
  <c:if test="${not empty errorMessage}">
    <p class="error">${errorMessage}</p>
  </c:if>
  <table>
    <tr>
      <th>ID</th>
      <th>Имя</th>
      <th>Действия</th>
    </tr>
    <c:forEach var="category" items="${categories}">
      <tr>
        <td>${category.id}</td>
        <td>${category.name}</td>
        <td>
          <form action="category-servlet" method="post" style="display:inline;">
            <input type="hidden" name="action" value="delete">
            <input type="hidden" name="id" value="${category.id}">
            <input type="submit" value="Удалить">
          </form>
        </td>
      </tr>
    </c:forEach>
  </table>

  <h2>Добавить новую категорию</h2>
  <form action="category-servlet" method="post" class="add-form">
    <input type="hidden" name="action" value="add">
    <label for="name">Имя:</label>
    <input type="text" id="name" name="name" required>
    <input type="submit" value="Добавить">
  </form>
</div>
</body>
</html>