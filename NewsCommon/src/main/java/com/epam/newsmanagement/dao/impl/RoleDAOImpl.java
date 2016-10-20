package com.epam.newsmanagement.dao.impl;

import com.epam.newsmanagement.dao.RoleDAO;
import com.epam.newsmanagement.entity.Role;
import com.epam.newsmanagement.manager.SQLQueryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class RoleDAOImpl implements RoleDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Autowired
    @Qualifier("dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Role> listRoles() {
        String SQL = SQLQueryManager.getProperty("RoleDAO.getAllRoles");
        List<Role> roleList = jdbcTemplateObject.query(SQL, new RoleMapper());
        return roleList;
    }
}
