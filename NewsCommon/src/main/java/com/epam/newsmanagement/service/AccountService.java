package com.epam.newsmanagement.service;

import com.epam.newsmanagement.entity.Account;
import com.epam.newsmanagement.entity.Role;

import java.util.List;

/**
 * Created by Natallia_Khadunai on 8/30/2016.
 */
public interface AccountService {
    Account create(Account account);
    Account getAccount(String login);
    List<Account> listAccounts();
    void update(Account account);
    void updateRole(Account account, Role role);
    void delete(Account account);
}
