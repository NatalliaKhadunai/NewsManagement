package com.epam.newsmanagement.dao.impl;

import com.epam.newsmanagement.dao.ArticleDAO;
import com.epam.newsmanagement.entity.Article;
import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.entity.Tag;
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
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Natallia_Khadunai on 8/24/2016.
 */
public class ArticleDAOImpl implements ArticleDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Autowired
    @Qualifier("dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public Article create(final Article article) {
        final String SQL = SQLQueryManager.getProperty("ArticleDAO.addArticle");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplateObject.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL, new String[]{"id"});
                preparedStatement.setString(1, article.getMainTitle());
                preparedStatement.setString(2, article.getShortTitle());
                preparedStatement.setString(3, article.getContent());
                Date date = new Date();
                Timestamp currTimestamp = new Timestamp(date.getTime());
                preparedStatement.setTimestamp(4, currTimestamp);
                return preparedStatement;
            }
        }, keyHolder);
        article.setId(keyHolder.getKey().intValue());
        String SQLArticleAuthor = SQLQueryManager.getProperty("Article_Author.addRow");
        for (Author author : article.getAuthorSet())
            if (author.isWorking()) jdbcTemplateObject.update( SQLArticleAuthor, article.getId(), author.getId());
        String SQLArticleTag = SQLQueryManager.getProperty("Article_Tag.addRow");
        for (Tag tag : article.getTagSet())
            jdbcTemplateObject.update( SQLArticleTag, article.getId(), tag.getId());
        return article;
    }

    public Article getArticleById(int id) {
        String SQL = SQLQueryManager.getProperty("ArticleDAO.getArticle");
        Article article = jdbcTemplateObject.queryForObject(SQL, new ArticleMapper(), id);
        return article;
    }

    public List<Article> listArticles(int pageNum, int pageSize) {
        String SQL = SQLQueryManager.getProperty("ArticleDAO.getAllArticles_Limit");
        List<Article> articleList = jdbcTemplateObject.query(SQL, new ArticleMapper(), pageNum,
                pageSize, pageNum, pageSize);
        return articleList;
    }

    public List<Article> listArticlesSortByDate(int pageNumber, int pageSize) {
        String SQL = SQLQueryManager.getProperty("ArticleDAO.getAllArticlesSortByDate_Limit");
        List<Article> articleList = jdbcTemplateObject.query(SQL, new ArticleMapper(), pageNumber,
                pageSize, pageNumber, pageSize);
        return articleList;
    }

    public List<Article> listArticlesSortByNumOfComments(int pageNumber, int pageSize) {
        String SQL = SQLQueryManager.getProperty("ArticleDAO.getAllArticlesSortByNumOfComments_Limit");
        List<Article> articleList = jdbcTemplateObject.query(SQL, new ArticleMapper(), pageNumber,
                pageSize, pageNumber, pageSize);
        return articleList;
    }

    public int getTotalCount() {
        String SQL = SQLQueryManager.getProperty("ArticleDAO.totalCount");
        int totalCount = jdbcTemplateObject.queryForObject(SQL, Integer.class);
        return totalCount;
    }

    public void editArticle(Article articleToEdit) {
        String SQL = SQLQueryManager.getProperty("ArticleDAO.editArticle");
        jdbcTemplateObject.update(SQL, articleToEdit.getMainTitle(), articleToEdit.getShortTitle(),
                articleToEdit.getContent(), articleToEdit.getPublishDate(), articleToEdit.getMainPhoto(), articleToEdit.getId());
    }

    public void delete(Article article){
        String SQL = SQLQueryManager.getProperty("ArticleDAO.deleteArticle");
        jdbcTemplateObject.update(SQL, article.getId());
    }
}
