<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<spring:url value="resources/css/NewsList.css" var="newsListCSS"/>
<link rel="stylesheet" type="text/css" href="${newsListCSS}">
<spring:url value="resources/css/TagFilter.css" var="tagFilterCSS"/>
<link rel="stylesheet" type="text/css" href="${tagFilterCSS}">
<spring:url value="resources/js/TagFilter.js" var="tagFilterJS"/>
<script src="${tagFilterJS}"></script>
<div class="content-container">
    <form class="filter-form" action="/NewsAdmin/main" method="get">
        <select class="author-filter" name="authorFilter">
            <option disabled>Select an author</option>
            <c:forEach var="author" items="${authorList}">
                <option value="${author.id}">
                        ${author.first_name} ${author.last_name}
                </option>
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
        <input type="submit" value="Filter">
    </form>
    <a class="button" href="/NewsAdmin/resetFilter">Reset filter</a>

    <form class="form-to-delete-news" action="/NewsAdmin/deleteArticle" method="post">
        <ul class="news-list">
            <c:forEach var="article" items="${articleList}">
                <li>
                    <div class="news-header">
                        <div class="news-main-title">
                            <a href="/NewsAdmin/newsPage?articleId=${article.id}">
                                ${article.mainTitle}
                            </a>
                        </div>
                        <div class="news-author">
                            <span>
                                (by ${article.authorSet})
                            </span>
                        </div>
                        <div class="news-publish-date">
                            <span>${article.publishDate}</span>
                        </div>
                    </div>
                    <p>
                        ${article.shortTitle}
                    </p>
                    <div class="news-footer">
                        <div class="news-tags">
                            <ul class="news-tag-list">
                                <c:forEach var="tag" items="${article.tagSet}">
                                    <li>${tag.name}</li>
                                </c:forEach>
                            </ul>
                        </div>
                        <div class="news-comments">
                            <span>
                                <spring:message code="label.comments"/> (${fn:length(article.commentList)})
                            </span>
                        </div>
                        <div class="news-edit">
                            <a href="/NewsAdmin/editArticle?articleId=${article.id}">
                                <spring:message code="label.edit"/>
                            </a>
                        </div>
                        <div class="check-to-delete">
                            <input type="checkbox" name="articleIds" value="${article.id}">
                        </div>
                    </div>
                </li>
            </c:forEach>
        </ul>
        <input class="right" type="submit" value="Delete">
    </form>
    <div class="pagination">
        <ul class="page-list">
            <c:forEach begin="1" end="${maxPageNo}" var="val">
                <c:choose>
                    <c:when test="${val eq page}">
                        <li class="current-menu-item">
                            <a href="/NewsAdmin/main?page=${val}">${val}</a>
                        </li>
                    </c:when>
                    <c:when test="${val != pageNo}">
                        <li>
                            <a href="/NewsAdmin/main?page=${val}">${val}</a>
                        </li>
                    </c:when>
                </c:choose>
            </c:forEach>
        </ul>
    </div>
</div>
