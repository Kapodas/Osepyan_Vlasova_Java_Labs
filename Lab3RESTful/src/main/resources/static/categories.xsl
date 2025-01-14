<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes"/>

    <xsl:template match="/">
        <html>
            <head>
                <title>Categories</title>
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
                <h1>Categories</h1>
                <table border="1">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                    </tr>
                    <xsl:for-each select="List/item">
                        <tr>
                            <!-- Кликабельный ID -->
                            <td>
                                <a href="/api/categories/{id}">
                                    <xsl:value-of select="id"/>
                                </a>
                            </td>
                            <!-- Кликабельное название -->
                            <td>
                                <a href="/api/categories/{id}">
                                    <xsl:value-of select="name"/>
                                </a>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>