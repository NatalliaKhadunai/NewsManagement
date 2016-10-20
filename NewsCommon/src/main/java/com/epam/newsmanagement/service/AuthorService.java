package com.epam.newsmanagement.service;

import com.epam.newsmanagement.entity.Article;
import com.epam.newsmanagement.entity.Author;

import java.util.List;

/**
 * Created by Natallia_Khadunai on 8/30/2016.
 */
public interface AuthorService {
    Author create(Author author);
    Author getAuthor(int id);
    List<Author> listAuthors();
    List<Author> listAuthorsNotExpired();
    void update(Author author);
    void delete(Author author);
}
