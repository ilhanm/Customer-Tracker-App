<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page pageEncoding="utf-8" %>
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

    <!--  add a search box -->
        <form:form action="search" method="GET">
            Search customer: <input type="text" name="theSearchName" />

            <input type="submit" value="Search" class="add-button" />
        </form:form>

        <table>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
            </tr>
            <c:forEach var="tempCustomer" items="${customers}">

                <c:url var="updateLink" value="/customer/showFormForUpdate">
                    <c:param name="customerId" value="${tempCustomer.id}" />
                </c:url>

                <c:url var="deleteLink" value="/customer/delete">
                    <c:param name="customerId" value="${tempCustomer.id}" />
                </c:url>

                <tr>
                 <td> ${tempCustomer.firstName}</td>
                 <td> ${tempCustomer.lastName}</td>
                 <td> ${tempCustomer.email} </td>
                  <td>
                      <a href="${updateLink}"> Update </a>
                    |
                      <a href="${deleteLink}"
                         onclick="if (!(confirm('Are you sure about deleting?'))) return false"> Delete </a>
                  </td>

                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>