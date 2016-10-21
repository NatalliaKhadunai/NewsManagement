package com.epam.newsmanagement.command;

import com.epam.newsmanagement.entity.Article;
import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.entity.Tag;
import com.epam.newsmanagement.service.ArticleService;
import com.epam.newsmanagement.service.AuthorService;
import com.epam.newsmanagement.service.TagService;
import com.epam.newsmanagement.controller.ClientController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.epam.newsmanagement.util.Filter;
import com.epam.newsmanagement.util.SortMode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Command for empty request. Returns main page
 */

public class EmptyCommand implements Command {
    @Autowired
    ArticleService articleService;
    @Autowired
    AuthorService authorService;
    @Autowired
    TagService tagService;
    private Logger logger = Logger.getLogger("CommandLogger");
    private SortMode currentSortMode = SortMode.NONE;
    private Filter filter = null;

    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        if (session.getAttribute("locale") == null) session.setAttribute("locale", "EN");
        String request = req.getParameter("request");

        int page = 1;
        if (req.getParameter("page") != null) page = Integer.parseInt(req.getParameter("page"));
        req.setAttribute("page", page);
        int maxPageNo = 0;

        List<Article> articleList = null;
        if (request == null) {
            articleList = getArticleSortedList(request, page);
            maxPageNo = (int) Math.ceil((double) articleService.totalNewsCount() / (double) ClientController.RECORD_PER_PAGE);
        }
        else if (request.equals("sort_by_date") || request.equals("sort_by_num_of_comments")) {
            articleList = getArticleSortedList(request, page);
            maxPageNo = (int) Math.ceil((double) articleService.totalNewsCount() / (double) ClientController.RECORD_PER_PAGE);
        }
        else if (request.equals("filter")) {
            articleList = getArticleFilteredList(req.getParameter("authorFilter"),
                    req.getParameterValues("tagFilter"), page);
            maxPageNo = (int) Math.ceil((double) articleList.size() / (double) ClientController.RECORD_PER_PAGE);
        }
        else if (request.equals("resetFilter")) filter = null;
        List<Author> authorList = authorService.listAuthors();
        List<Tag> tagList = tagService.listTags();
        req.setAttribute("articleList", articleList);
        req.setAttribute("authorList", authorList);
        req.setAttribute("tagList", tagList);
        req.setAttribute("maxPageNo", maxPageNo);
        try {
            if (request != null && request.equals("resetFilter")) resp.sendRedirect("/NewsClient/ClientController");
            else req.getRequestDispatcher("newsList").forward(req, resp);
        }
        catch (IOException | ServletException e) {
            logger.error(e);
        }
    }

    private List<Article> getArticleSortedList(String sortMode, int page) {
        List<Article> articleList = null;
        if (sortMode != null) currentSortMode = SortMode.valueOf(sortMode.toUpperCase());
        switch (currentSortMode) {
            case SORT_BY_DATE: {
                articleList = articleService.getArticlesSortByDate(page, ClientController.RECORD_PER_PAGE);
            }
            break;
            case SORT_BY_NUM_OF_COMMENTS: {
                articleList = articleService.getArticlesSortByNumOfComments(page, ClientController.RECORD_PER_PAGE);
            }
            break;
            case NONE: {
                articleList = articleService.getArticles(page, ClientController.RECORD_PER_PAGE);
            }
            break;
        }
        return articleList;
    }

    private List<Article> getArticleFilteredList(String authorIdStr, String[] tagIdStrArray, int page) {
        Set<Tag> tagSet = new HashSet<Tag>();
        for (String tagIdStr : tagIdStrArray) {
            tagSet.add(tagService.getTag(Integer.parseInt(tagIdStr)));
        }
        Author author = authorService.getAuthor(Integer.parseInt(authorIdStr));

        List<Article> articleList1 = articleService.getArticlesByAuthor(author);
        List<Article> articleList2 = tagService.searchByTags(tagSet);
        List<Article> articleIntersectionList = new ArrayList<Article>();
        for (Article article1 : articleList1) {
            if (articleList2.contains(article1)) articleIntersectionList.add(article1);
        }

        List<Article> articleResultList;
        if (articleIntersectionList.size() > ClientController.RECORD_PER_PAGE)
            articleResultList = articleIntersectionList.subList((page - 1) *
                    ClientController.RECORD_PER_PAGE  + 1, page *
                    ClientController.RECORD_PER_PAGE + 1);
        else articleResultList = articleIntersectionList;

        return articleResultList;
    }
}
