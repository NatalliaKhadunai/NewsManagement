<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url value="resources/css/Common.css" var="commonCSS"/>
<link rel="stylesheet" type="text/css" href="${commonCSS}">
<spring:url value="resources/css/Login.css" var="loginCSS"/>
<link rel="stylesheet" type="text/css" href="${loginCSS}">
<spring:url value="resources/css/Login.js" var="loginJS"/>
<script src="${loginJS}"></script>
<div class="content-container">
    <form id="loginForm" class="login-form" action="<c:url value='/j_spring_security_check' />" method="post">
        <table>
            <tr>
                <td><label for="username"><spring:message code="label.login"/>:</label></td>
                <td><input type="text" id="username" name="username" required></td>
            </tr>
            <tr>
                <td><label for="password"><spring:message code="label.password"/>:</label></td>
                <td><input type="password" id="password" name="password" required></td>
            </tr>
            <tr class="login-button">
                <td>
                    <a href="#">Registration</a>
                </td>
                <td><input type="submit" value="Login"/></td>
            </tr>
        </table>
    </form>
</div>
<div id="error" class="error">
    <p>${error}</p>
</div>
