package com.epam.newsmanagement.service;

import com.epam.newsmanagement.dao.AccountDAO;
import com.epam.newsmanagement.entity.Account;
import com.epam.newsmanagement.exception.AccountDoesNotExistException;
import com.epam.newsmanagement.exception.AccountExistsException;
import com.epam.newsmanagement.exception.InvalidAccountException;
import com.epam.newsmanagement.exception.WrongLoginOrPasswordException;
import com.epam.newsmanagement.service.impl.AccountServiceImpl;
import org.junit.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
/**
 * Created by Natallia_Khadunai on 8/30/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})

public class AccountServiceImplTest {
    @Autowired
    AccountDAO accountDAO;
    AccountServiceImpl accountService;

    @Before
    public void setUp() throws Exception {
        accountDAO = mock(AccountDAO.class);
        accountService = new AccountServiceImpl();
        accountService.setAccountDAO(accountDAO);
    }

    @Test
    public void createAccountTest() {
        Account account = new Account();
        when(accountDAO.create(any(Account.class))).thenReturn(account);
        Account accountTest = accountService.create(new Account());
        assertEquals(accountTest, account);
    }

    @Test (expected = AccountExistsException.class)
    public void createAccountNegativeTest_AccountExists(){
        Account account = new Account();
        when(accountDAO.getAccount(anyString())).thenReturn(account);
        Account accountTest = accountService.create(new Account());
    }

    @Test (expected = InvalidAccountException.class)
    public void createAccountNegativeTest_NullAccount() {
        accountService.create(null);
    }

    @Test
    public void getAccountTest() {
        Account account = new Account();
        when(accountDAO.getAccount(anyString())).thenReturn(account);
        Account accountTest = accountService.getAccount("1");
        assertEquals(account, accountTest);
    }

    @Test (expected = WrongLoginOrPasswordException.class)
    public void getAccountNegativeTest() {
        accountService.getAccount("            ");
    }

    @Test
    public void listAccountsTest() {
        when(accountDAO.listAccounts()).thenReturn(Arrays.asList(new Account(), new Account()));
        List<Account> accountList = accountService.listAccounts();
        assertTrue(accountList.size() > 0);
    }

    @Test (expected = InvalidAccountException.class)
    public void updateAccountNegativeTest_NullAccount() {
        accountService.update(null);
    }

    @Test (expected = AccountDoesNotExistException.class)
    public void updateAccountNegativeTest_AccountDoesNotExist() {
        when(accountDAO.getAccount(anyString())).thenReturn(null);
        Account accountTest = new Account();
        accountTest.setUsername("abc");
        accountService.update(accountTest);
    }

    @Test (expected = WrongLoginOrPasswordException.class)
    public void updateAccountNegativeTest_WrongLogin() {
        Account account = new Account();
        account.setUsername("");
        accountService.update(account);
    }

    @Test (expected = InvalidAccountException.class)
    public void deleteAccountNegativeTest_NullAccount() {
        accountService.delete(null);
    }

    @Test (expected = WrongLoginOrPasswordException.class)
    public void deleteAccountNegativeTest_WrongLogin() {
        Account account = new Account();
        account.setUsername("");
        accountService.delete(account);
    }

    @Test (expected = AccountDoesNotExistException.class)
    public void deleteAccountNegativeTest_AccountDoesNotExist() {
        when(accountDAO.getAccount(anyString())).thenReturn(null);
        Account account = new Account();
        account.setUsername("abc");
        accountService.delete(account);
    }
}
