package com.epam.newsmanagement.dao.impl;

import com.epam.newsmanagement.dao.CommentDAO;
import com.epam.newsmanagement.entity.Comment;
import com.epam.newsmanagement.manager.SQLQueryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CommentDAOImpl implements CommentDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Autowired
    @Qualifier("dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public Comment create(Comment comment) {
        final String SQL = SQLQueryManager.getProperty("CommentDAO.addComment");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplateObject.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL, new String[]{"id"});
                preparedStatement.setInt(1, comment.getArticleID());
                preparedStatement.setInt(2, comment.getAccountId());
                preparedStatement.setTimestamp(3, comment.getDate());
                preparedStatement.setString(4, comment.getContent());
                return preparedStatement;
            }
        }, keyHolder);
        comment.setId(keyHolder.getKey().longValue());
        return comment;
    }

    @Override
    public Comment getComment(int id) {
        String SQL = SQLQueryManager.getProperty("CommentDAO.getComment");
        Comment comment = jdbcTemplateObject.queryForObject(SQL, new CommentMapper(), id);
        return comment;
    }

    public List<Comment> listCommentByArticleId(int id) {
        String SQL = SQLQueryManager.getProperty("CommentDAO.getCommentsByArticleId");
        List<Comment> commentList = jdbcTemplateObject.query(SQL, new CommentMapper(), id);
        return commentList;
    }

    public List<Comment> listCommentByAccountLogin(int id) {
        String SQL = SQLQueryManager.getProperty("CommentDAO.getCommentsByAccountId");
        List<Comment> commentList = jdbcTemplateObject.query(SQL, new CommentMapper(), id);
        return commentList;
    }

    public void delete(Comment comment){
        String SQL = SQLQueryManager.getProperty("CommentDAO.deleteComment");
        jdbcTemplateObject.update(SQL, comment.getId());
    }
}
