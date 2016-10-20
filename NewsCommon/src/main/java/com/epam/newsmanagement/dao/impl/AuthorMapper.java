package com.epam.newsmanagement.dao.impl;

import com.epam.newsmanagement.entity.Author;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Natallia_Khadunai on 8/24/2016.
 */
public class AuthorMapper implements RowMapper<Author> {
    public Author mapRow(ResultSet resultSet, int i) throws SQLException {
        Author author = new Author();
        author.setId(resultSet.getInt("id"));
        author.setFirst_name(resultSet.getString("first_name"));
        author.setLast_name(resultSet.getString("last_name"));
        author.setWorking(resultSet.getString("is_working").charAt(0) == 'Y'?true:false);
        return author;
    }
}
