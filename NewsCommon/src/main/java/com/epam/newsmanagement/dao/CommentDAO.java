package com.epam.newsmanagement.dao;

import com.epam.newsmanagement.entity.Comment;

import java.util.List;

/**
 * Created by Natallia_Khadunai on 8/24/2016.
 */
public interface CommentDAO extends DAO{
    Comment create(Comment comment);
    Comment getComment(int id);
    List<Comment> listCommentByArticleId(int id);
    List<Comment> listCommentByAccountLogin(int id);
    void delete(Comment comment);
}
