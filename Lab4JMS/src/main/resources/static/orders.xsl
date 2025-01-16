<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes"/>

    <xsl:template match="/">
        <html>
            <head>
                <title>Orders</title>
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
                    a {
                    color: #007bff;
                    text-decoration: none;
                    }
                    a:hover {
                    text-decoration: underline;
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <h1>Orders</h1>
                    <table border="1">
                        <tr>
                            <th>ID</th>
                            <th>Customer ID</th>
                            <th>Product ID</th>
                            <th>Order Date</th>
                            <th>Quantity</th>
                        </tr>
                        <xsl:for-each select="List/item">
                            <tr>
                                <!-- Кликабельный ID -->
                                <td>
                                    <a href="/api/orders/{id}">
                                        <xsl:value-of select="id"/>
                                    </a>
                                </td>
                                <!-- Кликабельный Customer ID -->
                                <td>
                                    <a href="/api/customers/{customerId}">
                                        <xsl:value-of select="customerId"/>
                                    </a>
                                </td>
                                <!-- Кликабельный Product ID -->
                                <td>
                                    <a href="/api/products/{productId}">
                                        <xsl:value-of select="productId"/>
                                    </a>
                                </td>
                                <!-- Дата заказа -->
                                <td>
                                    <xsl:value-of select="orderDate"/>
                                </td>
                                <!-- Количество -->
                                <td>
                                    <xsl:value-of select="quantity"/>
                                </td>
                            </tr>
                        </xsl:for-each>
                    </table>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>