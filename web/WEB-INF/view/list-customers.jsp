<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType = "text/html;charset = UTF-8" language = "java" %>
<!DOCTYPE html>
<html>

<head>
    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css" />
<title> Title </title>

</head>

<body>

<div id="wrapper">
    <div id="header">
        <h2>CRM - Customer Relationship Manager </h2>
    </div>
    </div>

<div id="container">
    <div id="content">

<%--        Add Customer Button--%>
        <input type="button" value="Add Customer" onclick="window.location.href ='showFormForAdd'; return false;"
               class="add-button"
        />
        <table>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
            </tr>
            <c:forEach var="tempCustomer" items="${customers}">
                <tr> <td> ${tempCustomer.firstName}</td>
                 <td> ${tempCustomer.lastName}</td>
                 <td> ${tempCustomer.email}</tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>