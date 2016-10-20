package com.epam.newsmanagement.dao.impl;

import com.epam.newsmanagement.dao.TagDAO;
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
import java.util.List;

/**
 * Created by Natallia_Khadunai on 8/24/2016.
 */
public class TagDAOImpl implements TagDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Autowired
    @Qualifier("dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public Tag create(final Tag tag) {
        final String SQL = SQLQueryManager.getProperty("TagDAO.addTag");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplateObject.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL, new String[]{"id"});
                preparedStatement.setString(1, tag.getName());
                return preparedStatement;
            }
        }, keyHolder);
        tag.setId(keyHolder.getKey().intValue());
        return tag;
    }

    public Tag getTag(int id) {
        String SQL = SQLQueryManager.getProperty("TagDAO.getTag");
        Tag tag = jdbcTemplateObject.queryForObject(SQL, new TagMapper(), id);
        return tag;
    }

    public List<Tag> listTags() {
        String SQL = SQLQueryManager.getProperty("TagDAO.getAllTags");
        List<Tag> tagList = jdbcTemplateObject.query(SQL, new TagMapper());
        return tagList;
    }

    public void update(Tag tag) {
        String SQL = SQLQueryManager.getProperty("TagDAO.updateTag");
        jdbcTemplateObject.update(SQL, tag.getName(), tag.getId());
    }

    public void delete(Tag tag){
        String SQL = SQLQueryManager.getProperty("TagDAO.deleteTag");
        jdbcTemplateObject.update(SQL, tag.getId());
    }
}
