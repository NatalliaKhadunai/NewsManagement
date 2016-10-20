<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:choose>
    <c:when test="${sessionScope.locale eq 'EN'}">
        <fmt:setLocale value="en_US" scope="session"/>
    </c:when>
    <c:when test="${sessionScope.locale eq 'RU'}">
        <fmt:setLocale value="ru_RU" scope="session"/>
    </c:when>
</c:choose>
<fmt:setBundle basename="label" var="rb"/>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="label.login" bundle="${rb}"/></title>
</head>
<body>

<div class="container">
    <div class="row">
        <form action="/NewsClient/ClientController?request=login" method="post">
            <p><fmt:message key="label.login" bundle="${rb}"/>:</p>
            <input type="text" name="login">
            <p><fmt:message key="label.password" bundle="${rb}"/>:</p>
            <input type="password" name="password">
            <input type="submit">
        </form>
        <a href="Registry.jsp"><fmt:message key="label.registration" bundle="${rb}"/></a>
    </div>
</div>

<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
