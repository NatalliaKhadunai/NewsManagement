package com.epam.newsmanagement.dao;

import com.epam.newsmanagement.entity.Article;
import com.epam.newsmanagement.entity.Author;

import java.util.List;
import java.util.Set;

/**
 * Created by Natallia_Khadunai on 9/26/2016.
 */
public interface ArticleAuthorDAO {
    void attachAuthor(Author author, Article article);
    List<Integer> searchByArticle(Article article);
    List<Integer> searchByAuthors(Set<Author> tagSet);
    List<Integer> searchByAuthor(Author author);
}
