package com.epam.newsmanagement.command;

import com.epam.newsmanagement.entity.Account;
import com.epam.newsmanagement.entity.Comment;
import com.epam.newsmanagement.service.AccountService;
import com.epam.newsmanagement.service.CommentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.sql.Timestamp;

/**
 * Command for adding comment to database
 */

public class AddCommentCommand implements Command {
    @Autowired
    CommentService commentService;
    @Autowired
    AccountService accountService;

    private Logger logger = Logger.getLogger("CommandLogger");

    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        int articleId = Integer.parseInt(req.getParameter("articleId"));
        String content = req.getParameter("commentArea");
        Date date = new Date();
        Timestamp currTimestamp = new Timestamp(date.getTime());
        HttpSession session = req.getSession(false);
        String login = ((Account) session.getAttribute("loggedUser")).getUsername();
        Account account = accountService.getAccount(login);
        Comment comment = new Comment(articleId, account.getId(), currTimestamp, content);
        commentService.addComment(comment);
        try {
            resp.sendRedirect("/NewsClient/ClientController?request=articlePage&articleId="
                    + articleId);
        }
        catch (IOException e) {
            logger.error(e);
        }
    }
}
