package com.epam.newsmanagement.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.epam.newsmanagement.dao.ArticleTagDAO;
import com.epam.newsmanagement.dao.TagDAO;
import com.epam.newsmanagement.entity.Tag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
public class ArticleTagDAOTest {
    @Autowired
    ArticleTagDAO articleTagDAO;
    @Autowired
    TagDAO tagDAO;

    @Test
    public void searchByTagTest() {
        Tag tagTest = tagDAO.getTag(1);
        List<Integer> articleIdList = articleTagDAO.searchByTag(tagTest);
        assertEquals(articleIdList.size(), 1);
    }

    @Test
    public void searchByTagsTest() {
        Tag tagTest = tagDAO.getTag(1);
        Set<Tag> tagSet = new HashSet<Tag>();
        tagSet.add(tagTest);
        List<Integer> articleIdList = articleTagDAO.searchByTags(tagSet);
        assertEquals(articleIdList.size(), 1);
    }
}
