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
    <title><fmt:message key="label.header" bundle="${rb}"/> </title>
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
        <button type="button" name="back" onclick="history.back()">Back</button>
    </div>
    <div class="row">
        <h3>${article.mainTitle}<small>${article.publishDate}</small></h3>
    </div>
    <div class="row">
        <p>${article.content}</p>
    </div>
    <div class="row">
        <p><fmt:message key="label.tags" bundle="${rb}"/> :</p>
        <c:forEach var="tag" items="${article.tagSet}">
            <span class="label label-default">${tag.name}</span>
        </c:forEach>
    </div>
    <div class="row">
        <p><fmt:message key="label.authors" bundle="${rb}"/>:</p>
        <c:forEach var="author" items="${article.authorSet}">
            <span class="label label-primary">${author.first_name} ${author.last_name}</span>
        </c:forEach>
    </div>
    <div class="row">
        <p><fmt:message key="label.comments" bundle="${rb}"/>:</p>
        <dl>
            <c:forEach var="comment" items="${article.commentList}">
                <dt>${comment.accountId} ${comment.date}</dt>
                <dd>${comment.content}</dd>
            </c:forEach>
        </dl>
        <div class="row">
            <form action="/NewsClient/ClientController?request=addComment&articleId=${article.id}" method="post">
                <textarea id="commentArea" name="commentArea"></textarea>
                <input type="submit">
            </form>
        </div>
    </div>
    <div class="row">
        <ul class="pager">
            <li><a href="/NewsClient/ClientController?request=previousArticle&currArticleId=${article.id}">Previous</a></li>
            <li><a href="/NewsClient/ClientController?request=nextArticle&currArticleId=${article.id}">Next</a></li>
        </ul>
    </div>
</div>

<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
