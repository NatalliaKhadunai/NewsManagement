package com.epam.newsmanagement.dao.impl;

import com.epam.newsmanagement.entity.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by user on 10/16/2016.
 */
public class RoleMapper implements RowMapper<Role> {
    public Role mapRow(ResultSet resultSet, int i) throws SQLException {
        Role role = new Role();
        role.setId(resultSet.getInt("id"));
        role.setName(resultSet.getString("role_name"));
        return role;
    }
}
