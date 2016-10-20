package com.epam.newsmanagement.service;

import com.epam.newsmanagement.entity.Comment;

import java.util.List;

/**
 * Created by Natallia_Khadunai on 8/29/2016.
 */
public interface CommentService {
    Comment addComment(Comment comment);
    Comment getComment(int id);
    List<Comment> listCommentByArticleId(int id);
    List<Comment> listCommentByAccountLogin(int id);
    void delete(Comment comment);
}
