package com.epam.newsmanagement.service;

import com.epam.newsmanagement.dao.ArticleDAO;
import com.epam.newsmanagement.dao.ArticleTagDAO;
import com.epam.newsmanagement.dao.TagDAO;
import com.epam.newsmanagement.entity.Article;
import com.epam.newsmanagement.entity.Tag;
import com.epam.newsmanagement.exception.EmptySetException;
import com.epam.newsmanagement.exception.InvalidArticleException;
import com.epam.newsmanagement.exception.InvalidIdException;
import com.epam.newsmanagement.exception.InvalidTagException;
import com.epam.newsmanagement.service.impl.TagServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
/**
 * Created by Natallia_Khadunai on 8/30/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
public class TagServiceImplTest {
    TagDAO tagDAO;
    ArticleTagDAO articleTagDAO;
    ArticleDAO articleDAO;
    TagServiceImpl tagService;
    Tag tag;
    Article article;

    @Before
    public void setUp() throws Exception {
        tagDAO = mock(TagDAO.class);
        articleTagDAO = mock(ArticleTagDAO.class);
        articleDAO = mock(ArticleDAO.class);
        tagService = new TagServiceImpl();
        tagService.setTagDAO(tagDAO);
        tagService.setArticleTagDAO(articleTagDAO);
        tagService.setArticleDAO(articleDAO);

        tag = new Tag();
        tag.setId(1);
        tag.setName("1");
        article = new Article();
        article.setId(1);
    }

    @Test
    public void createTagTest() {
        when(tagDAO.create(any(Tag.class))).thenReturn(tag);
        Tag tagTest = tagService.createTag(new Tag());
        assertEquals(tagTest.getId(), tag.getId());
    }

    @Test (expected = InvalidTagException.class)
    public void createTagNegativeTest() {
        Tag tagTest = tagService.createTag(null);
    }

    @Test
    public void getTagTest() {
        when(tagDAO.getTag(anyInt())).thenReturn(tag);
        Tag tagTest = tagService.getTag(1);
        assertEquals(tagTest.getId(), tag.getId());
    }

    @Test (expected = InvalidIdException.class)
    public void getTagNegativeTest() {
        Tag tag = tagService.getTag(-1);
    }

    @Test
    public void listTagsTest() {
        when(tagDAO.listTags()).thenReturn(Arrays.asList(new Tag(), new Tag()));
        List<Tag> tagList = tagService.listTags();
        assertTrue(tagList.size() > 0);
    }

    @Test (expected = InvalidTagException.class)
    public void deleteTagNegativeTest_NullTag() {
        tagService.deleteTag(null);
    }

    @Test (expected = InvalidIdException.class)
    public void deleteTagNegativeTest_NegativeId() {
        Tag tagTest = new Tag();
        tagTest.setId(-1);
        tagService.deleteTag(tagTest);
    }

    @Test (expected = InvalidTagException.class)
    public void attachTagNegativeTest_NullTag() {
        Article articleTest = mock(Article.class);
        tagService.attachTag(null, articleTest);
    }

    @Test (expected = InvalidIdException.class)
    public void attachTagNegativeTest_NegativeTagId() {
        Article articleTest = mock(Article.class);
        Tag tagTest = new Tag();
        tagTest.setId(-1);
        tagService.attachTag(tagTest, articleTest);
    }

    @Test (expected = InvalidArticleException.class)
    public void attachTagNegativeTest_NullArticle() {
        tagService.attachTag(tag, null);
    }

    @Test (expected = InvalidIdException.class)
    public void attachTagNegativeTest_NegativeArticleId() {
        Tag tagTest = mock(Tag.class);
        Article articleTest = new Article();
        articleTest.setId(-1);
        tagService.attachTag(tagTest, articleTest);
    }

    @Test (expected = EmptySetException.class)
    public void searchByTagsNegativeTest_NullSet() {
        tagService.searchByTags(null);
    }

    @Test (expected = EmptySetException.class)
    public void searchByTagsNegativeTest_EmptySet() {
        tagService.searchByTags(new HashSet<Tag>());
    }

    @Test
    public void totalNewsCountForEachTagTest() {
        when(tagDAO.listTags()).thenReturn(new ArrayList<Tag>() {
            {
                add(tag);
            }
        });
        when(articleTagDAO.searchByTag(any(Tag.class))).thenReturn(new ArrayList<Integer>());
        Map<Tag, Integer> mapTest = tagService.totalNewsCountForEachTag();
        assertEquals(mapTest.get(tag).intValue(), 0);
    }
}
