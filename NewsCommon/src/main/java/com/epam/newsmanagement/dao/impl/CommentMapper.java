package com.epam.newsmanagement.dao.impl;

import com.epam.newsmanagement.entity.Comment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Natallia_Khadunai on 8/24/2016.
 */
public class CommentMapper implements RowMapper<Comment> {
    public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
        Comment comment = new Comment();
        comment.setId(resultSet.getLong("id"));
        comment.setArticleID(resultSet.getInt("article_id"));
        comment.setAccountId(resultSet.getInt("account_id"));
        comment.setContent(resultSet.getString("content"));
        comment.setDate(resultSet.getTimestamp("publish_date"));
        return comment;
    }
}
