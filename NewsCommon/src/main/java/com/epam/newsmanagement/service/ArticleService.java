package com.epam.newsmanagement.service;

import com.epam.newsmanagement.entity.Article;
import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.entity.Tag;

import java.util.List;
import java.util.Set;

/**
 * Created by Natallia_Khadunai on 8/29/2016.
 */
public interface ArticleService {
    List<Article> getArticles(int pageNum, int pageSize);
    List<Article> getArticlesSortByDate(int pageNum, int pageSize);
    List<Article> getArticlesSortByNumOfComments(int pageNum, int pageSize);
    List<Article> getArticlesByAuthor(Author author);
    List<Article> getArticlesByAuthors(Set<Author> authors);
    List<Article> getArticleByTag(Tag tag);
    List<Article> getArticleByTags(Set<Tag> tags);
    Article getArticle(int id);
    Article addArticle(Article article);
    void editArticle(Article articleToEdit);
    void deleteArticle(Article ... articles);
    int totalNewsCount();
    void attachAuthor(Author author, Article article);
}
