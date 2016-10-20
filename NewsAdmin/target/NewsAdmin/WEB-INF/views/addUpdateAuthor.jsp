<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url value="resources/css/AddUpdateCommon.css" var="addUpdateCommonCSS"/>
<link rel="stylesheet" type="text/css" href="${addUpdateCommonCSS}">
<spring:url value="resources/js/AddUpdateAuthor.js" var="addUpdateAuthorJS"/>
<script src="${addUpdateAuthorJS}"></script>

<div class="content-container">
    <c:forEach items="${authorList}" var="author">
        <label for="${author.id}">Author:</label>
        <input id="${author.id}" name="authorName" type="text" value="${author.first_name} ${author.last_name}" disabled/>
        <button id="Edit${author.id}" name="${author.id}">Edit</button>
        <div class="hidden" id="update-delete-forms-${author.id}">
            <form class="update-form" name="updateAuthorForm" action="/NewsAdmin/updateAuthor" method="post">
                <input type="hidden" name="authorId" value="${author.id}">
                <input type="submit" value="Update">
            </form>
            <form class="delete-form" name="deleteAuthorForm" action="/NewsAdmin/expireAuthor" method="post">
                <input type="hidden" name="authorId" value="${author.id}">
                <input type="submit" value="Expire">
            </form>
        </div>
        <br>
    </c:forEach>

    <form id="addAuthorForm" action="/NewsAdmin/addAuthor" method="post">
        <label for="addAuthor">Add author:</label>
        <input id="addAuthor" name="authorName" type="text" required>
        <input type="submit" value="Save">
    </form>
    <div id="error" class="error">

    </div>
</div>
