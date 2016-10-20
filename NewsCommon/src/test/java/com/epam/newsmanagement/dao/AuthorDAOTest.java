package com.epam.newsmanagement.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.epam.newsmanagement.dao.AuthorDAO;
import com.epam.newsmanagement.entity.Author;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.List;

import static org.junit.Assert.*;
/**
 * Created by Natallia_Khadunai on 8/30/2016.
 */
@DatabaseSetup(value = "classpath:/dataset.xml", type = DatabaseOperation.CLEAN_INSERT)
@DatabaseTearDown(value = "classpath:/dataset.xml", type = DatabaseOperation.DELETE_ALL)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-test-config.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class AuthorDAOTest {
    @Autowired
    AuthorDAO authorDAO;

    @Test
    public void getAuthorTest() {
        Author author = authorDAO.getAuthor(1);
        assertEquals(author.getFirst_name(), "ONE");
    }

    @Test
    public void listAuthorsTest() {
        List<Author> authorList = authorDAO.listAuthors();
        assertTrue(authorList.size() > 0);
    }
}
