package com.epam.newsmanagement.service.impl;

import com.epam.newsmanagement.dao.CommentDAO;
import com.epam.newsmanagement.entity.Comment;
import com.epam.newsmanagement.exception.InvalidCommentException;
import com.epam.newsmanagement.exception.InvalidIdException;
import com.epam.newsmanagement.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDAO commentDAO;

    public void setCommentDAO(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    public Comment addComment(Comment comment) {
        if (comment != null) return commentDAO.create(comment);
        else throw new InvalidCommentException("Comment shouldn't be null");
    }

    @Override
    public Comment getComment(int id) {
        if (id > 0) return commentDAO.getComment(id);
        else throw new InvalidIdException("Comment id must be more than 0");
    }

    public List<Comment> listCommentByArticleId(int id) {
        return commentDAO.listCommentByArticleId(id);
    }

    public List<Comment> listCommentByAccountLogin(int id) {
        return commentDAO.listCommentByAccountLogin(id);
    }

    public void delete(Comment comment) {
        if (comment != null) commentDAO.delete(comment);
        else throw new InvalidCommentException("Comment shouldn't be null");
    }


}
