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
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><fmt:message key="label.header" bundle="${rb}"/></title>
</head>
<body>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">
                <fmt:message key="label.header" bundle="${rb}"/>
            </a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li>
                <a href="/NewsClient/ClientController?request=setLocale&locale=EN">
                    <fmt:message key="label.en" bundle="${rb}"/>
                </a>
            </li>
            <li>
                <a href="/NewsClient/ClientController?request=setLocale&locale=RU">
                    <fmt:message key="label.ru" bundle="${rb}"/>
                </a>
            </li>
            <li>
                <a href="/NewsClient/ClientController?request=logout">
                    <fmt:message key="label.logout" bundle="${rb}"/>
                </a>
            </li>
        </ul>
    </div>
</nav>

<div class="container">
    <div class="row">
        <form action="/NewsClient/ClientController?request=filter" method="post">
            <h3><fmt:message key="label.filter" bundle="${rb}"/>:</h3>
            <h4><fmt:message key="label.authors" bundle="${rb}"/>:</h4>
            <select name="authorFilter">
                <c:forEach var="author" items="${authorList}">
                    <option value="${author.id}">
                    ${author.first_name} ${author.last_name}
                    </option>
                </c:forEach>
            </select>
            <h4><fmt:message key="label.tags" bundle="${rb}"/>:</h4>
            <select name="tagFilter" multiple>
                <c:forEach var="tag" items="${tagList}">
                    <option value="${tag.id}">${tag.name}</option>
                </c:forEach>
            </select>
            <input type="submit" class="btn">
        </form>
        <a class="btn" href="/NewsClient/ClientController?request=resetFilter">Reset filter</a>

        <div class="dropdown">
            <button class="btn dropdown-toggle" type="button" data-toggle="dropdown">
                <fmt:message key="label.sortBy" bundle="${rb}"/>
                <span class="caret"></span>
            </button>
            <ul id="sorting" class="dropdown-menu">
                <li>
                    <a href="/NewsClient/ClientController?request=sort_by_date">
                        <fmt:message key="label.date" bundle="${rb}"/>
                    </a>
                </li>
                <li>
                    <a href="/NewsClient/ClientController?request=sort_by_num_of_comments">
                        <fmt:message key="label.numOfComments" bundle="${rb}"/>
                    </a>
                </li>
            </ul>
        </div>
        <c:forEach var="news" items="${newsList}">
            <div class="row">
                <h4>
                    <a href="/NewsClient/ClientController?request=articlePage&articleId=${news.id}">${news.mainTitle}</a>
                    <small>${news.publishDate}</small>
                </h4>
                <p>${news.shortTitle}</p>
                <p><fmt:message key="label.tags" bundle="${rb}"/>:</p>
                <c:forEach var="tag" items="${news.tagSet}">
                    <span class="label label-default">${tag.name}</span>
                </c:forEach>
                <p><fmt:message key="label.authors" bundle="${rb}"/>:</p>
                <c:forEach var="author" items="${news.authorSet}">
                    <span class="label label-primary">${author.first_name} ${author.last_name}</span>
                </c:forEach>
                <p><fmt:message key="label.comments" bundle="${rb}"/>:
                    <span class="badge">${fn:length(news.commentList)}</span>
                </p>
            </div>
        </c:forEach>
    </div>
    <div class="row">
        <ul class="pagination">
            <c:forEach begin="1" end="${maxPageNo}" var="val">
                <c:choose>
                    <c:when test="${val eq page}">
                        <li class="active">
                            <a href="/NewsClient/ClientController?page=${val}">${val}</a>
                        </li>
                    </c:when>
                    <c:when test="${val != pageNo}">
                        <li>
                            <a href="/NewsClient/ClientController?page=${val}">${val}</a>
                        </li>
                    </c:when>
                </c:choose>
            </c:forEach>
        </ul>

    </div>

    <footer class="footer">
        <p class="text-muted">Copyright (c) Epam 2015.</p>
    </footer>
</div>

<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
