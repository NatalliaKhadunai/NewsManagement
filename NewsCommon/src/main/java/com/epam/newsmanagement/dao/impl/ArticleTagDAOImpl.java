package com.epam.newsmanagement.dao.impl;

import com.epam.newsmanagement.dao.ArticleTagDAO;
import com.epam.newsmanagement.entity.Article;
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
import java.util.*;

/**
 * Created by Natallia_Khadunai on 8/26/2016.
 */
public class ArticleTagDAOImpl implements ArticleTagDAO {
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

    public void attachTag(int articleId, int tagId) {
        String SQL = SQLQueryManager.getProperty("Article_Tag.addRow");
        jdbcTemplateObject.update(SQL, articleId, tagId);
    }

    public List<Integer> searchByArticle(Article article) {
        String SQL = SQLQueryManager.getProperty("Article_Tag.searchByArticle");
        RowMapper<Integer> rowMapper = new RowMapper<Integer>() {
            public Integer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                return resultSet.getInt("tag_id");
            }
        };
        List<Integer> tagIdList = jdbcTemplateObject.query(SQL, rowMapper, article.getId());
        return tagIdList;
    }

    public List<Integer> searchByTag(Tag tag) {
        Set<Tag> tagSet = new HashSet<Tag>();
        tagSet.add(tag);
        return searchByTags(tagSet);
    }

    public List<Integer> searchByTags(Set<Tag> tagSet) {
        String SQL = SQLQueryManager.getProperty("Article_Tag.searchByTags");
        RowMapper<Integer> rowMapper = new RowMapper<Integer>() {
            public Integer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                return resultSet.getInt("ARTICLE_ID");
            }
        };
        List<Integer> tagIdList = new ArrayList<Integer>();
        for (Tag tag : tagSet) tagIdList.add(tag.getId());
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("tag_id", tagIdList);
        parameters.addValue("tag_count", tagSet.size());
        List<Integer> articleIdList = namedParameterJdbcTemplate.query(SQL, parameters, rowMapper);
        return articleIdList;
    }
}
