package com.epam.newsmanagement.dao.impl;

import com.epam.newsmanagement.entity.Tag;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Natallia_Khadunai on 8/24/2016.
 */
public class TagMapper implements RowMapper<Tag> {
    public Tag mapRow(ResultSet resultSet, int i) throws SQLException {
        Tag tag = new Tag();
        tag.setId(resultSet.getInt("id"));
        tag.setName(resultSet.getString("name"));
        return tag;
    }
}
