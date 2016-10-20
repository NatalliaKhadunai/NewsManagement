package com.epam.newsmanagement.command;

import com.epam.newsmanagement.entity.Account;
import com.epam.newsmanagement.exception.AccountExistsException;
import com.epam.newsmanagement.service.AccountService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Command for registration
 */

public class RegistryCommand implements Command {
    @Autowired
    AccountService accountService;
    private Logger logger = Logger.getLogger("CommandLogger");

    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        if (accountService.getAccount(login) != null) throw new AccountExistsException("Account already exists!");
        else {
            try {
                String passwordStr = req.getParameter("password");
                System.out.println(login + " " + passwordStr);
                int password = computePassword(passwordStr);
                Account account = new Account(login, password);
                account = accountService.create(account);
                HttpSession session = req.getSession();
                session.setAttribute("loggedUser", account);
                resp.sendRedirect("/NewsClient/ClientController");
            }
            catch (IOException | NoSuchAlgorithmException e) {
                logger.error(e);
            }
        }
    }

    private int computePassword(String passwordStr) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(passwordStr.getBytes(), 0, passwordStr.length());
        BigInteger passwordHash = new BigInteger(1, messageDigest.digest());
        return passwordHash.intValue();
    }
}
