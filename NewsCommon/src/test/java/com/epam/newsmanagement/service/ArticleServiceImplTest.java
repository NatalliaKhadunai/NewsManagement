package com.epam.newsmanagement.service;

import static org.junit.Assert.*;

import com.epam.newsmanagement.dao.*;
import com.epam.newsmanagement.entity.Article;
import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.entity.Comment;
import com.epam.newsmanagement.entity.Tag;
import com.epam.newsmanagement.exception.*;
import com.epam.newsmanagement.service.impl.ArticleServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;

/**
 * Created by Natallia_Khadunai on 8/29/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
public class ArticleServiceImplTest {
    ArticleServiceImpl articleService;
    ArticleDAO articleDAO;
    CommentDAO commentDAO;
    AuthorDAO authorDAO;
    ArticleTagDAO articleTagDAO;
    ArticleAuthorDAO articleAuthorDAO;

    List<Article> articleList;
    Article article;
    Author author;
    List<Comment> commentList;

    @Before
    public void setUp() throws Exception {
        articleDAO = mock(ArticleDAO.class);
        commentDAO = mock(CommentDAO.class);
        articleTagDAO = mock(ArticleTagDAO.class);
        authorDAO = mock(AuthorDAO.class);
        articleAuthorDAO = mock(ArticleAuthorDAO.class);

        articleService = new ArticleServiceImpl();
        articleService.setArticleDAO(articleDAO);
        articleService.setCommentDAO(commentDAO);
        articleService.setArticleTagDAO(articleTagDAO);
        articleService.setAuthorDAO(authorDAO);
        articleService.setArticleAuthorDAO(articleAuthorDAO);

        article = new Article();
        article.setId(1);
        articleList = new ArrayList<Article>();
        articleList.add(article);
        author = new Author();
        author.setId(1);
        commentList = new ArrayList<Comment>();
        commentList.add(new Comment());
    }

    @Test
    public void getArticlesSortByDateTest() {
        when(articleDAO.listArticlesSortByDate(anyInt(), anyInt())).thenReturn(articleList);
        when(articleTagDAO.searchByArticle(any(Article.class))).thenReturn(new ArrayList<Integer>());
        when(articleAuthorDAO.searchByArticle(any(Article.class))).thenReturn(new ArrayList<Integer>());
        List<Article> articleList = articleService.getArticlesSortByDate(1, 1);
        assertEquals(articleList.isEmpty(), false);
    }

    @Test (expected = InvalidPageNumberOrSizeException.class)
    public void getArticlesSortByDateNegativeTest() {
        List<Article> articleList = articleService.getArticlesSortByDate(0, 0);
    }

    @Test
    public void getArticlesSortByNumOfCommentsTest() {
        when(articleDAO.listArticlesSortByNumOfComments(anyInt(), anyInt())).thenReturn(articleList);
        when(articleTagDAO.searchByArticle(any(Article.class))).thenReturn(new ArrayList<Integer>());
        when(articleAuthorDAO.searchByArticle(any(Article.class))).thenReturn(new ArrayList<Integer>());
        when(commentDAO.listCommentByArticleId(anyInt())).thenReturn(new ArrayList<Comment>());
        List<Article> articleList = articleService.getArticlesSortByNumOfComments(1, 1);
        assertEquals(articleList.isEmpty(), false);
    }

    @Test (expected = InvalidPageNumberOrSizeException.class)
    public void getArticlesSortByNumOfCommentsNegativeTest() {
        List<Article> articleList = articleService.getArticlesSortByDate(0, 0);
    }

    @Test
    public void getArticleByIdTest() {
        when(articleDAO.getArticleById(article.getId())).thenReturn(article);
        Article articleTest = articleService.getArticle(article.getId());
        assertEquals(articleTest.getId(), article.getId());
    }

    @Test (expected = InvalidIdException.class)
    public void getArticleByIdNegativeTest() {
        Article articleTest = articleService.getArticle(-1);
    }

    @Test
    public void addArticleTest() {
        when(articleDAO.create(any(Article.class))).thenReturn(article);
        Set<Author> authorSet = mock(Set.class);
        Set<Tag> tagSet = mock(Set.class);
        Article articleTest = articleService.addArticle(new Article());
        assertEquals(articleTest.getId(), article.getId());
    }

    @Test (expected = InvalidArticleException.class)
    public void addArticleNegativeTest_NullArticle() {
        Set<Author> authorSet = mock(Set.class);
        Set<Tag> tagSet = mock(Set.class);
        Article articleTest = articleService.addArticle(null);
    }


    @Test (expected = InvalidArticleException.class)
    public void editArticleNegativeTest() {
        articleService.editArticle(null);
    }

    @Test (expected = InvalidArticleException.class)
    public void deleteArticleNegativeTest() {
        articleService.deleteArticle(null);
    }

    @Test
    public void totalNewsCountTest() {
        when(articleDAO.getTotalCount()).thenReturn(articleList.size());
        assertEquals(articleService.totalNewsCount(), articleList.size());
    }

    @Test
    public void getArticlesByAuthorTest() {
        when(articleAuthorDAO.searchByAuthor(any(Author.class))).thenReturn(new ArrayList<Integer>() {
            {add(1);}
        });
        when(articleDAO.getArticleById(anyInt())).thenReturn(article);
        List<Article> articleList = articleService.getArticlesByAuthor(author);
        assertTrue(articleList.size() > 0);
    }

    @Test (expected = InvalidAuthorException.class)
    public void getArticlesByAuthorNegativeTest_NullAuthor() {
        List<Article> articleList = articleService.getArticlesByAuthor(null);
    }

    @Test (expected = InvalidAuthorException.class)
    public void getArticlesByAuthorNegativeTest_InvalidAuthorId() {
        Author author = new Author();
        author.setId(-1);
        List<Article> articleList = articleService.getArticlesByAuthor(author);
    }
}
