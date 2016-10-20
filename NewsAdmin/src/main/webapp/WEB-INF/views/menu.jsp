<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<nav>
    <ul class="menu">
        <li class="current-menu-item">
            <a href="/NewsAdmin/main"><spring:message code="label.newsList"/></a>
        </li>
        <li>
            <a href="/NewsAdmin/addArticle"><spring:message code="label.addArticle"/></a>
        </li>
        <li>
            <a href="/NewsAdmin/addAuthor"><spring:message code="label.addUpdateAuthor"/></a>
        </li>
        <li>
            <a href="/NewsAdmin/addTag"><spring:message code="label.addUpdateTag"/></a>
        </li>
    </ul>
</nav>