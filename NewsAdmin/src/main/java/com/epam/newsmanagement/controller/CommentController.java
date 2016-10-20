package com.epam.newsmanagement.controller;

import com.epam.newsmanagement.entity.Account;
import com.epam.newsmanagement.entity.Comment;
import com.epam.newsmanagement.service.AccountService;
import com.epam.newsmanagement.service.CommentService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Controller processes the requests for the comments
 */

@Controller
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public String addComment(@RequestParam(value = "commentContent", required = true) String commentContent,
                             @RequestParam(value = "articleId", required = true) String articleIdStr,
                             Principal principal) {
        Account account = accountService.getAccount(principal.getName());
        Date date = new Date();
        Timestamp currTimestamp = new Timestamp(date.getTime());
        Comment comment = new Comment(Integer.parseInt(articleIdStr), account.getId(), currTimestamp, commentContent);
        commentService.addComment(comment);
        return "redirect:/NewsAdmin/main";
    }

    @RequestMapping(value = "/deleteComment", method = RequestMethod.GET)
    public String deleteComment(@RequestParam(value = "commentId", required = true) String commentIdStr,
                                ModelMap model) {
        int commentId = Integer.parseInt(commentIdStr);
        Comment comment = commentService.getComment(commentId);
        commentService.delete(comment);
        return "redirect:/NewsAdmin/main";
    }
}
