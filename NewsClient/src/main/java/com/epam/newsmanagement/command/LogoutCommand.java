package com.epam.newsmanagement.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutCommand implements Command {
    private Logger logger = Logger.getLogger("CommandLogger");
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        session.removeAttribute("loggedUser");
        try {
            resp.sendRedirect("/NewsClient/ClientController");
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
