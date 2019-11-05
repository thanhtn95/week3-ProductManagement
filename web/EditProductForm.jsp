<%@ page import="model.product" %><%--
  Created by IntelliJ IDEA.
  User: z-one
  Date: 11/5/19
  Time: 4:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<%product selectedProduct = (product) request.getAttribute("selectedProduct");%>
<form method="post" action="${pageContext.request.contextPath}/product">
    <fieldset style="width: fit-content; height: fit-content">
        <legend><h2>Add new product</h2></legend>
        <p>ID:&nbsp;<input type="number" name="id" readonly="true" value="<%=selectedProduct.getId()%>"></p>
        <p>Name:&nbsp;<input type="text" name="name" value="<%=selectedProduct.getName()%>"></p>
        <p>Price:&nbsp;<input type="number" name="price" value="<%=selectedProduct.getPrice()%>"></p>
        <p>Quantity:&nbsp;<input type="number" name="quantity" value="<%=selectedProduct.getQuantity()%>"></p>
        <p>Description:&nbsp;<input type="text" name="description" value="<%=selectedProduct.getDescription()%>"></p>
    </fieldset>
    <p><input type="submit" name="action" value="Edit" onclick="return confirm('Are You Sure?')"></p>
</form>
<p><a href="product?action=view">
    <button type="button">Back</button>
</a></p>
</body>
</html>