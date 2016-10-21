<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url value="resources/css/AddUpdateNews.css" var="addUpdateNewsCSS"/>
<link rel="stylesheet" type="text/css" href="${addUpdateNewsCSS}">
<spring:url value="resources/css/TagFilter.css" var="tagFilterCSS"/>
<link rel="stylesheet" type="text/css" href="${tagFilterCSS}">
<spring:url value="resources/js/TagFilter.js" var="tagFilterJS"/>
<script src="${tagFilterJS}"></script>
<spring:url value="resources/js/AddUpdateNews.js" var="addUpdateNewsJS"/>
<script src="${addUpdateNewsJS}"></script>
<div class="content-container">
    <form id="addNewsForm" action="/NewsAdmin/${action}Article" method="post">
        <input type="hidden" name="articleId" value="${article.id}">
        <label for="mainTitle"><spring:message code="label.mainTitle"/> :</label>
        <input name="mainTitle" id="mainTitle" value="${article.mainTitle}"/>
        <br>
        <label for="shortTitle"><spring:message code="label.shortTitle"/> :</label>
        <input name="shortTitle" id="shortTitle" value="${article.shortTitle}"/>
        <br>
        <label for="content"><spring:message code="label.content"/> :</label>
        <textarea name="content" id="content">${article.content}</textarea>
        <br>
        <select class="author-filter" name="authorFilter" multiple>
            <option disabled>Select an author</option>
            <c:forEach items="${authorList}" var="author">
                <option value="${author.id}">${author.first_name} ${author.last_name}</option>
            </c:forEach>
        </select>
        <div class="tag-filter">
            <div class="selectBox" id="tagSelect">
                <select>
                </select>
            </div>
            <div class="tag-filter-checkboxes" id="checkboxes">
                <label for="selectTag">
                    <input type="checkbox" id="selectTag" disabled/>Select tag
                </label>
                <c:forEach var="tag" items="${tagList}">
                    <label for="${tag.name}">
                        <input name="tagFilter" type="checkbox" id="${tag.name}" value="${tag.id}"/>${tag.name}
                    </label>
                </c:forEach>
            </div>
        </div>
        <br>
        <input type="submit" value="Save">
    </form>
    <div id="error" class="error">

    </div>
</div>
