<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Электронный магазин</title>
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
        .menu {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }
        .menu a {
            margin: 0 15px;
            padding: 10px 20px;
            text-decoration: none;
            color: #fff;
            background-color: #007bff;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        .menu a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Электронный магазин</h1>
    <div class="menu">
        <a href="customer-servlet">Просмотреть таблицу клиентов</a>
        <a href="category-servlet">Просмотреть таблицу категорий</a>
        <a href="order-servlet">Просмотреть таблицу заказов</a>
        <a href="product-servlet">Просмотреть таблицу продуктов</a>
    </div>
</div>
</body>
</html>