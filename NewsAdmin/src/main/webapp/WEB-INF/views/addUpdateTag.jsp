<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<spring:url value="resources/css/AddUpdateCommon.css" var="addUpdateCommonCSS"/>
<link rel="stylesheet" type="text/css" href="${addUpdateCommonCSS}">
<spring:url value="resources/js/AddUpdateTag.js" var="addUpdateTagJS"/>
<script src="${addUpdateTagJS}"></script>
<div class="content-container">
    <c:forEach items="${tagList}" var="tag">
        <label>Tag:</label>
        <input id="${tag.id}" name="tagName" type="text" value="${tag.name}" disabled/>
        <button id="Edit${tag.id}" name="${tag.id}">Edit</button>
        <div class="hidden" id="update-delete-forms-${tag.id}">
            <form class="update-form" name="updateTagForm" action="/NewsAdmin/updateTag" method="post">
                <input type="hidden" name="tagId" value="${tag.id}">
                <input type="submit" value="Update">
            </form>
            <form class="delete-form" name="deleteTagForm" action="/NewsAdmin/deleteTag" method="post">
                <input type="hidden" name="tagId" value="${tag.id}">
                <input type="submit" value="Delete">
            </form>
        </div>
        <br>
    </c:forEach>
    <form id="addTagForm" action="/NewsAdmin/addTag" method="post">
        <label for="addTag">Add tag:</label>
        <input id="addTag" name="tagName" type="text" required>
        <input type="submit" value="Save">
    </form>
    <div id="error" class="error">
    </div>
</div>