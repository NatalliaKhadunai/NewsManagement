package com.epam.newsmanagement.dao;

import com.epam.newsmanagement.entity.Article;
import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.entity.Tag;

import java.util.List;
import java.util.Set;

public interface ArticleDAO extends DAO {
    Article create(Article article);
    Article getArticleById(int id);
    List<Article> listArticles(int pageNum, int pageSize);
    List<Article> listArticlesSortByDate(int pageNumber, int pageSize);
    List<Article> listArticlesSortByNumOfComments(int pageNumber, int pageSize);
    int getTotalCount();
    void editArticle(Article articleToEdit);
    void delete(Article article);
}
