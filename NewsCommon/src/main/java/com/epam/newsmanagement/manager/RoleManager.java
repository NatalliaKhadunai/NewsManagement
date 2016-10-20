package com.epam.newsmanagement.manager;

import com.epam.newsmanagement.dao.impl.AccountMapper;
import com.epam.newsmanagement.dao.impl.RoleMapper;
import com.epam.newsmanagement.entity.Account;
import com.epam.newsmanagement.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class RoleManager {
    private static DataSource dataSource;
    private static JdbcTemplate jdbcTemplateObject;
    private static List<Role> roleList = new ArrayList<>();

   /* static {
        String SQL = SQLQueryManager.getProperty("RoleDAO.getAllRoles");
        roleList = jdbcTemplateObject.query(SQL, new RoleMapper());
    }

    @Autowired
    @Qualifier("dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }*/

    public Role getRole(String name) {
        for (Role role : roleList) {
            if (role.getName().equals(name)) return role;
        }
        return null;
    }
}
