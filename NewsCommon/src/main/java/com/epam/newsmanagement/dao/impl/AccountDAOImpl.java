package com.epam.newsmanagement.dao.impl;

import com.epam.newsmanagement.dao.AccountDAO;
import com.epam.newsmanagement.entity.Account;
import com.epam.newsmanagement.manager.SQLQueryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AccountDAOImpl implements AccountDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Autowired
    @Qualifier ("dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public Account create(final Account account) {
        final String SQL = SQLQueryManager.getProperty("AccountDAO.addAccount");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplateObject.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL, new String[]{"id"});
                preparedStatement.setString(1, account.getUsername());
                preparedStatement.setInt(2, account.getPassword());
                return preparedStatement;
            }
        }, keyHolder);
        account.setId(keyHolder.getKey().intValue());
        return account;
    }

    public Account getAccount(String login) {
        String SQL = SQLQueryManager.getProperty("AccountDAO.getAccount");
        Account account = null;
        try {
            account = jdbcTemplateObject.queryForObject(SQL, new AccountMapper(), login);
        }
        catch (EmptyResultDataAccessException e) {

        }
        return account;
    }

    public List<Account> listAccounts() {
        String SQL = SQLQueryManager.getProperty("AccountDAO.getAllAccounts");
        List<Account> accountList = jdbcTemplateObject.query(SQL, new AccountMapper());
        return accountList;
    }

    public void update(Account account) {
        String SQL = SQLQueryManager.getProperty("AccountDAO.updateAccount");
        jdbcTemplateObject.update(SQL, account.getPassword(), account.getUsername());
    }

    public void delete(Account account){
        String SQL = SQLQueryManager.getProperty("AccountDAO.deleteAccount");
        jdbcTemplateObject.update(SQL, account.getUsername());
        return;
    }
}
