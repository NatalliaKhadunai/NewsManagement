<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url value="resources/css/NewsPage.css" var="newsPageCSS"/>
<link rel="stylesheet" type="text/css" href="${newsPageCSS}">
<div class="content-container">
    <a onclick="history.back()">Back</a>
    <div class="news-header">
        <div>
            <b>
                ${article.mainTitle}
            </b>
        </div>
        <div class="news-author">
            (by ${article.authorSet})
        </div>
        <div class="news-publish-date">
            ${article.publishDate}
        </div>
    </div>
    <div class="news-content">
        <p>
            ${article.content}
        </p>
    </div>
    <div class="news-comments">
        <dl>
            <c:forEach var="comment" items="${article.commentList}">
                <dt>${comment.accountId} ${comment.date}</dt>
                <dd>
                    <div class="comment">
                        <div class="delete-button">
                            <a href="/NewsAdmin/deleteComment?commentId=${comment.id}" class="button">X</a>
                        </div>
                        ${comment.content}
                    </div>
                </dd>
            </c:forEach>
        </dl>
        <form class="add-comment-form" action="/NewsAdmin/addComment" method="post">
            <textarea name="commentContent"></textarea>
            <input type="hidden" name="articleId" value="${article.id}">
            <input type="submit" value="Post comment">
        </form>
    </div>
    <div class="pagination">
        <div class="previous">
            <a href="#">PREVIOUS</a>
        </div>
        <div class="next">
            <a href="#">NEXT</a>
        </div>
    </div>
</div>