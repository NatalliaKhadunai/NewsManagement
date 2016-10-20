package com.epam.newsmanagement.command;

import com.epam.newsmanagement.entity.Article;
import com.epam.newsmanagement.service.ArticleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Command for forming article page
 */

public class ArticlePageCommand implements Command {
    @Autowired
    ArticleService articleService;
    private Logger logger = Logger.getLogger("CommandLogger");

    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        int articleId = Integer.parseInt(req.getParameter("articleId"));
        Article article = articleService.getArticle(articleId);
        req.setAttribute("article", article);
        try {
            req.getRequestDispatcher("/NewsPage.jsp").forward(req, resp);
        }
        catch (IOException | ServletException e) {
            logger.error(e);
        }
    }
}
