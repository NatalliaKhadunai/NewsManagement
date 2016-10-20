<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><spring:message code="label.header"/></title>

    <spring:url value="resources/css/Common.css" var="commonCSS"/>
    <link rel="stylesheet" type="text/css" href="${commonCSS}">

    <spring:url value="resources/js/jquery-3.1.1.min.js" var="jqueryJS"/>
    <script src="${jqueryJS}"></script>
</head>
<body>
<tiles:insertAttribute name="header"/>
<div class="container">
    <sec:authorize access="isAuthenticated()">
        <tiles:insertAttribute name="menu"/>
    </sec:authorize>
    <tiles:insertAttribute name="content"/>
</div>
<tiles:insertAttribute name="footer"/>
</body>
</html>