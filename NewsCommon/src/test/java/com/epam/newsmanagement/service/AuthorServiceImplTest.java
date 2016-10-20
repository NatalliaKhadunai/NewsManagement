package com.epam.newsmanagement.service;

import com.epam.newsmanagement.dao.AuthorDAO;
import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.exception.InvalidAuthorException;
import com.epam.newsmanagement.exception.InvalidIdException;
import com.epam.newsmanagement.service.impl.AuthorServiceImpl;
import org.junit.Before;
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

public class AuthorServiceImplTest {
    @Autowired
    AuthorDAO authorDAO;
    AuthorServiceImpl authorService;

    @Before
    public void setUp() throws Exception {
        authorDAO = mock(AuthorDAO.class);
        authorService = new AuthorServiceImpl();
        authorService.setAuthorDAO(authorDAO);
    }

    @Test
    public void createAuthorTest() {
        Author author = new Author();
        when(authorDAO.create(any(Author.class))).thenReturn(author);
        Author authorTest = authorService.create(new Author());
        assertEquals(author, authorTest);
    }

    @Test (expected = InvalidAuthorException.class)
    public void createAuthorNegativeTest() {
        authorService.create(null);
    }

    @Test
    public void getAuthorTest() {
        Author author = new Author();
        when(authorDAO.getAuthor(anyInt())).thenReturn(author);
        Author authorTest = authorService.getAuthor(1);
        assertEquals(author, authorTest);
    }

    @Test (expected = InvalidIdException.class)
    public void getAuthorNegativeTest() {
        authorService.getAuthor(-1);
    }

    @Test
    public void listAuthorsTest() {
        when(authorDAO.listAuthors()).thenReturn(Arrays.asList(new Author(), new Author()));
        List<Author> authorList = authorService.listAuthors();
        assertTrue(authorList.size() > 0);
    }

    @Test (expected = InvalidAuthorException.class)
    public void updateAuthorNegativeTest_NullAuthor() {
        authorService.update(null);
    }

    @Test (expected = InvalidIdException.class)
    public void updateAuthorNegativeTest_NegativeId() {
        Author author = new Author();
        author.setId(-1);
        authorService.update(author);
    }

    @Test (expected = InvalidAuthorException.class)
    public void deleteAuthorNegativeTest_NullAuthor() {
        authorService.delete(null);
    }

    @Test (expected = InvalidIdException.class)
    public void deleteAuthorNegativeTest_NegativeId() {
        Author author = new Author();
        author.setId(-1);
        authorService.delete(author);
    }
}
