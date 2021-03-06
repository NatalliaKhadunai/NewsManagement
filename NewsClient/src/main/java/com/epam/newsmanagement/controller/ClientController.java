package com.epam.newsmanagement.controller;

import com.epam.newsmanagement.command.Command;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Main com.epam.newsmanagement.controller of application. Resolves incoming requests
 */

public class ClientController extends HttpServlet {
    public static final int RECORD_PER_PAGE = 3;
    private WebApplicationContext webApplicationContext;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        ServletContext servletContext = config.getServletContext();
        webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String request = req.getParameter("request");
        if (session.getAttribute("loggedUser") == null && request == null)
            resp.sendRedirect("/NewsClient/Login.jsp");
        else {
            String beanName;
            if (request != null) beanName = request + "Command";
            else beanName = "emptyCommand";
            Command command = (Command) webApplicationContext.getBean(beanName);
            command.execute(req, resp);
        }
    }
}
