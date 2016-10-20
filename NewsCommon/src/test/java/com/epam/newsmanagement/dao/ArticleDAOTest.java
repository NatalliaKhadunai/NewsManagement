package com.epam.newsmanagement.dao;
import static org.junit.Assert.*;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.epam.newsmanagement.dao.ArticleDAO;
import com.epam.newsmanagement.entity.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.List;

/**
 * Created by Natallia_Khadunai on 8/29/2016.
 */
@DatabaseSetup(value = "classpath:/dataset.xml", type = DatabaseOperation.CLEAN_INSERT)
@DatabaseTearDown(value = "classpath:/dataset.xml", type = DatabaseOperation.DELETE_ALL)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-test-config.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class ArticleDAOTest {
    @Autowired
    ArticleDAO articleDAO;

    @Test
    public void getArticleByIdTest() {
        Article article = articleDAO.getArticleById(1);
        assertEquals(article.getMainTitle(), "FIRST_TEST");
    }

    @Test
    public void listArticlesTest() {
        List<Article> articleList = articleDAO.listArticles(1,1);
        assertTrue(articleList.size() > 0);
    }

    @Test
    public void listArticlesSortByDateTest() {
        List<Article> articleListSortByDate = articleDAO.listArticlesSortByDate(1,2);
        assertTrue(articleListSortByDate.size() > 0);
        assertTrue(articleListSortByDate.get(0).getPublishDate().after(articleListSortByDate.get(1).getPublishDate()));
    }

    @Test
    public void listArticlesSortByNumOfCommentsTest() {
        List<Article> articleListSortByNumOfComments = articleDAO.listArticlesSortByNumOfComments(1,2);
        assertTrue(articleListSortByNumOfComments.size() > 0);
    }

    @Test
    public void getTotalCountTest() {
        assertTrue(articleDAO.getTotalCount() > 0);
    }
}
