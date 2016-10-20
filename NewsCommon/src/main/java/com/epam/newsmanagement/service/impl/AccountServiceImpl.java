package com.epam.newsmanagement.service.impl;

import com.epam.newsmanagement.dao.AccountDAO;
import com.epam.newsmanagement.entity.Account;
import com.epam.newsmanagement.entity.Role;
import com.epam.newsmanagement.exception.AccountDoesNotExistException;
import com.epam.newsmanagement.exception.AccountExistsException;
import com.epam.newsmanagement.exception.InvalidAccountException;
import com.epam.newsmanagement.exception.WrongLoginOrPasswordException;
import com.epam.newsmanagement.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountDAO accountDAO;

    public void setAccountDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Account create(Account account) {
        if (account != null) {
            if (!accountExists(account)) return accountDAO.create(account);
            else throw new AccountExistsException("Account with such login already exists");
        }
        else throw new InvalidAccountException("Account shouldn't be null");
    }

    public Account getAccount(String login) {
        if (login.trim().length() != 0) return accountDAO.getAccount(login);
        else throw new WrongLoginOrPasswordException();
    }

    public List<Account> listAccounts() {
        return accountDAO.listAccounts();
    }

    public void update(Account account) {
        if (account == null) throw new InvalidAccountException("Account shouldn't be null");
        if (account.getUsername().length() == 0) throw new WrongLoginOrPasswordException();
        if (accountExists(account)) accountDAO.update(account);
        else throw new AccountDoesNotExistException("Account does not exists");
    }

    @Override
    public void updateRole(Account account, Role role) {

    }

    public void delete(Account account) {
        if (account == null) throw new InvalidAccountException("Account shouldn't be null");
        if (account.getUsername().length() == 0) throw new WrongLoginOrPasswordException();
        if (accountExists(account)) accountDAO.delete(account);
        else throw new AccountDoesNotExistException("Account does not exists");
    }

    private boolean accountExists(Account account) {
        if (accountDAO.getAccount(account.getUsername()) != null) return true;
        else return false;
    }
}
