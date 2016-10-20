package com.epam.newsmanagement.dao.impl;

import com.epam.newsmanagement.dao.ArticleAuthorDAO;
import com.epam.newsmanagement.entity.Article;
import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.entity.Tag;
import com.epam.newsmanagement.manager.SQLQueryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Natallia_Khadunai on 9/26/2016.
 */
public class ArticleAuthorDAOImpl implements ArticleAuthorDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    @Qualifier("dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public void attachAuthor(Author author, Article article) {
        String SQL = SQLQueryManager.getProperty("Article_Author.addRow");
        jdbcTemplateObject.update(SQL, article.getId(), author.getId());
    }

    public List<Integer> searchByArticle(Article article) {
        String SQL = SQLQueryManager.getProperty("Article_Author.searchByArticle");
        RowMapper<Integer> rowMapper = new RowMapper<Integer>() {
            public Integer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                return resultSet.getInt("author_id");
            }
        };
        List<Integer> authorIdList = jdbcTemplateObject.query(SQL, rowMapper, article.getId());
        return authorIdList;
    }


    public List<Integer> searchByAuthor(Author author) {
        Set<Author> authorSet = new HashSet<Author>();
        authorSet.add(author);
        return searchByAuthors(authorSet);
    }

    public List<Integer> searchByAuthors(Set<Author> authorSet) {
        String SQL = SQLQueryManager.getProperty("Article_Author.searchByAuthors");
        RowMapper<Integer> rowMapper = new RowMapper<Integer>() {
            public Integer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                return resultSet.getInt("ARTICLE_ID");
            }
        };
        List<Integer> authorIdList = new ArrayList<Integer>();
        for (Author author : authorSet) authorIdList.add(author.getId());
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("author_id", authorIdList);
        parameters.addValue("author_count", authorSet.size());
        List<Integer> articleIdList = namedParameterJdbcTemplate.query(SQL, parameters, rowMapper);
        return articleIdList;
    }
}
