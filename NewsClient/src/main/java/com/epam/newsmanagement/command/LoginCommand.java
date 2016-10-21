package com.epam.newsmanagement.command;

import com.epam.newsmanagement.entity.Account;
import com.epam.newsmanagement.exception.WrongLoginOrPasswordException;
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
 * Command for login
 */

public class LoginCommand implements Command {
    @Autowired
    AccountService accountService;
    private Logger logger = Logger.getLogger("CommandLogger");

    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        int password = countPasswordHash(req.getParameter("password"));
        Account account = accountService.getAccount(login);
        if (account != null) {
            if (account.getPassword() != password)
                throw new WrongLoginOrPasswordException("Wrong password or login!");
            else {
                HttpSession session = req.getSession();
                session.setAttribute("loggedUser", account);
                try {
                    resp.sendRedirect("/NewsClient/ClientController");
                } catch (IOException e) {
                    logger.error(e);
                }
            }
        }
        else throw new WrongLoginOrPasswordException("Wrong password or login!");
    }

    private int countPasswordHash(String passwordStr) {
        int passwordHash = 0;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(passwordStr.getBytes(), 0, passwordStr.length());
            passwordHash = new BigInteger(1, messageDigest.digest()).intValue();
        }
        catch (NoSuchAlgorithmException e) {
            logger.error(e);
        }
        return passwordHash;
    }
}
