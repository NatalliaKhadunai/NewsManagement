<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<header>
    <div class="header-text">
    <a href="/NewsAdmin/main">
        <spring:message code="label.header"/>
    </a>
</div>
    <div class="user-panel">
        <sec:authorize access="isAuthenticated()">
            <p>
                Hello, <sec:authentication property="principal.username" />
                <a class="button" href="<c:url value='/j_spring_security_logout' />">Logout</a>
            </p>
            </sec:authorize>
    <ul class="header-lang">
        <li>
            <a href="?language=en"><spring:message code="label.en"/></a>
        </li>
        <li>
            <a href="?language=ru"><spring:message code="label.ru"/></a>
        </li>
    </ul>
</div>
</header>