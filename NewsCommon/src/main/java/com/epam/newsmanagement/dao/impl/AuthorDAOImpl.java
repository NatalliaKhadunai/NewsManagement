package com.epam.newsmanagement.dao.impl;

import com.epam.newsmanagement.dao.AuthorDAO;
import com.epam.newsmanagement.entity.Author;
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

/**
 * Created by Natallia_Khadunai on 8/24/2016.
 */
public class AuthorDAOImpl implements AuthorDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Autowired
    @Qualifier("dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public Author create(final Author author) {
        final String SQL = SQLQueryManager.getProperty("AuthorDAO.addAuthor");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplateObject.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL, new String[]{"id"});
                preparedStatement.setString(1, author.getFirst_name());
                preparedStatement.setString(2, author.getLast_name());
                return preparedStatement;
            }
        }, keyHolder);
        author.setId(keyHolder.getKey().intValue());
        return author;
    }

    public Author getAuthor(int id) {
        String SQL = SQLQueryManager.getProperty("AuthorDAO.getAuthor");
        Author author = jdbcTemplateObject.queryForObject(SQL, new AuthorMapper(), id);
        return author;
    }

    public List<Author> listAuthors() {
        String SQL = SQLQueryManager.getProperty("AuthorDAO.getAllAuthors");
        List<Author> authorList = jdbcTemplateObject.query(SQL, new AuthorMapper());
        return authorList;
    }

    public List<Author> listAuthorsNotExpired() {
        String SQL = SQLQueryManager.getProperty("AuthorDAO.getAllAuthorsNotExpired");
        List<Author> authorList = jdbcTemplateObject.query(SQL, new AuthorMapper());
        return authorList;
    }

    public void update(Author author) {
        String SQL = SQLQueryManager.getProperty("AuthorDAO.updateAuthor");
        jdbcTemplateObject.update(SQL, author.getFirst_name(), author.getLast_name(), author.getId());
    }

    public void delete(Author author){
        String SQL = SQLQueryManager.getProperty("AuthorDAO.deleteAuthor");
        jdbcTemplateObject.update(SQL, author.getId());
    }
}
