package com.epam.newsmanagement.dao.impl;

import com.epam.newsmanagement.entity.Article;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Natallia_Khadunai on 8/24/2016.
 */
public class ArticleMapper implements RowMapper<Article> {
    public Article mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Article article = new Article();
        article.setId(resultSet.getInt("id"));
        article.setMainTitle(resultSet.getString("main_title"));
        article.setShortTitle(resultSet.getString("short_title"));
        article.setContent(resultSet.getString("content"));
        article.setPublishDate(resultSet.getTimestamp("publish_date"));
        article.setMainPhoto(resultSet.getString("main_photo"));
        return article;
    }
}
