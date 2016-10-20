package com.epam.newsmanagement.service.impl;

import com.epam.newsmanagement.dao.AuthorDAO;
import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.exception.InvalidAuthorException;
import com.epam.newsmanagement.exception.InvalidIdException;
import com.epam.newsmanagement.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Natallia_Khadunai on 8/30/2016.
 */
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    AuthorDAO authorDAO;

    public void setAuthorDAO(AuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }

    public Author create(Author author) {
        if (author != null) return authorDAO.create(author);
        else throw new InvalidAuthorException("Author shouldn't be null");
    }

    public Author getAuthor(int id) {
        if (id > 0) return authorDAO.getAuthor(id);
        else throw new InvalidIdException("Id should be more tah 0");
    }

    public List<Author> listAuthors() {
        return authorDAO.listAuthors();
    }

    public List<Author> listAuthorsNotExpired() {
        return authorDAO.listAuthorsNotExpired();
    }

    public void update(Author author) {
        if (author == null) throw new InvalidAuthorException("Author shouldn't be null");
        if (author.getId() <= 0) throw new InvalidIdException("Id should be more than 0");
        authorDAO.update(author);
    }

    public void delete(Author author) {
        if (author == null) throw new InvalidAuthorException("Author shouldn't be null");
        if (author.getId() <= 0) throw new InvalidIdException("Id should be more than 0");
        authorDAO.delete(author);
    }
}
