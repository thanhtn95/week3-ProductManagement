<%--
  Created by IntelliJ IDEA.
  User: z-one
  Date: 11/5/19
  Time: 2:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>$Title$</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="myStyle.css">
</head>
<body>
<fieldset style="width: fit-content; height: fit-content">
    <legend><h2>Product List</h2></legend>
    <table>
        <tr>
            <td>ID</td>
            <td>Product Name</td>
            <td>Price</td>
            <td>Quantity</td>
            <td>Description</td>
        </tr>
        <c:forEach var="product" items="${productList}">
            <tr class="notfirstTr">
                <td>${product.getId()}</td>
                <td>${product.getName()}</td>
                <td>${product.getPrice()}</td>
                <td>${product.getQuantity()}</td>
                <td>${product.getDescription()}</td>
                <td><a href="${pageContext.request.contextPath}/product?action=delete&id=${product.getId()}" onclick="return confirm('Are You Sure?')">
                    <button type="button">Delete</button>
                </a></td>
                <td><a href="${pageContext.request.contextPath}/product?action=getSelected&id=${product.getId()}">
                    <button type="button">Edit</button>
                </a></td>
            </tr>
        </c:forEach>
    </table>
</fieldset>
<p><a href="addProductForm.jsp"><button type="button">Add new product</button></a></p>
</body>
</html>
