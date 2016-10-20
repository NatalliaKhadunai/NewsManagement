package com.epam.newsmanagement.service.impl;

import com.epam.newsmanagement.dao.*;
import com.epam.newsmanagement.entity.*;
import com.epam.newsmanagement.exception.*;
import com.epam.newsmanagement.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Natallia_Khadunai on 8/29/2016.
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    ArticleDAO articleDAO;
    CommentDAO commentDAO;
    TagDAO tagDAO;
    AuthorDAO authorDAO;
    ArticleTagDAO articleTagDAO;
    ArticleAuthorDAO articleAuthorDAO;

    @Autowired
    public void setArticleDAO(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }
    @Autowired
    public void setCommentDAO(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }
    @Autowired
    public void setTagDAO(TagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }
    @Autowired
    public void setArticleTagDAO(ArticleTagDAO articleTagDAO) {
        this.articleTagDAO = articleTagDAO;
    }
    @Autowired
    public void setArticleAuthorDAO(ArticleAuthorDAO articleAuthorDAO) {
        this.articleAuthorDAO = articleAuthorDAO;
    }
    @Autowired
    public void setAuthorDAO(AuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }

    public List<Article> getArticles(int pageNum, int pageSize) {
        if (pageNum < 1 || pageSize < 1) throw new InvalidPageNumberOrSizeException("Page number and its size should be more or equal 1");
        List<Article> articleList = articleDAO.listArticles(pageNum, pageSize);
        return fillArticlesInfo(articleList);
    }

    public List<Article> getArticlesSortByDate(int pageNum, int pageSize) {
        if (pageNum < 1 || pageSize < 1) throw new InvalidPageNumberOrSizeException("Page number and its size should be more or equal 1");
        List<Article> articleList = articleDAO.listArticlesSortByDate(pageNum, pageSize);
        return fillArticlesInfo(articleList);
    }

    public List<Article> getArticlesSortByNumOfComments(int pageNum, int pageSize) {
        if (pageNum < 1 || pageSize < 1) throw new InvalidPageNumberOrSizeException("Page number and its size should be more or equal 1");
        List<Article> articleList = articleDAO.listArticlesSortByNumOfComments(pageNum, pageSize);
        return fillArticlesInfo(articleList);
    }

    public List<Article> getArticlesByAuthor(Author author) {
        if (author == null) throw new InvalidAuthorException("Author shouldn't be null");
        if (author.getId() <= 0) throw new InvalidAuthorException("Author id is invalid");
        List<Integer> articleIdList = articleAuthorDAO.searchByAuthor(author);
        List<Article> articleList = new ArrayList<Article>();
        for (Integer articleId : articleIdList) {
            Article articleTmp = getArticle(articleId);
            articleList.add(fillArticleInfo(articleTmp));
        }
        return articleList;
    }

    public List<Article> getArticlesByAuthors(Set<Author> authors) {
        if (authors == null || authors.isEmpty()) throw new EmptySetException("Set<Author> shouldn't be null");
        List<Integer> articleIdList = articleAuthorDAO.searchByAuthors(authors);
        List<Article> articleList = new ArrayList<Article>();
        for (Integer articleId : articleIdList) {
            Article articleTmp = getArticle(articleId);
            articleList.add(fillArticleInfo(articleTmp));
        }
        return articleList;
    }

    public List<Article> getArticleByTag(Tag tag) {
        if (tag == null) throw new InvalidTagException("Tag shouldn't be null");
        if (tag.getId() <= 0) throw new InvalidTagException("Tag id is invalid");
        List<Integer> articleIdList = articleTagDAO.searchByTag(tag);
        List<Article> articleList = new ArrayList<Article>();
        for (Integer articleId : articleIdList) {
            Article articleTmp = getArticle(articleId);
            articleList.add(fillArticleInfo(articleTmp));
        }
        return articleList;
    }

    public List<Article> getArticleByTags(Set<Tag> tags) {
        if (tags == null || tags.isEmpty()) throw new EmptySetException("Set<Tag> shouldn't be empty");
        List<Integer> articleIdList = articleTagDAO.searchByTags(tags);
        List<Article> articleList = new ArrayList<Article>();
        for (Integer articleId : articleIdList) {
            Article articleTmp = getArticle(articleId);
            articleList.add(fillArticleInfo(articleTmp));
        }
        return articleList;
    }

    private Article fillArticleInfo(Article article) {
        article.setAuthorSet(getArticleAuthorSet(article));
        article.setTagSet(getArticleTagSet(article));
        article.setCommentList(getArticleCommentList(article));
        return article;
    }

    private List<Article> fillArticlesInfo(List<Article> articleList) {
        for (Article article : articleList) {
            article.setAuthorSet(getArticleAuthorSet(article));
            article.setTagSet(getArticleTagSet(article));
            article.setCommentList(getArticleCommentList(article));
        }
        return articleList;
    }

    private Set<Tag> getArticleTagSet(Article article) {
        List<Integer> tagIdList = articleTagDAO.searchByArticle(article);
        Set<Tag> tagSet = new HashSet<Tag>();
        if (tagIdList.size() > 0) {
            for (Integer tagId : tagIdList) {
                Tag tag = tagDAO.getTag(tagId);
                tagSet.add(tag);
            }
        }
        return tagSet;
    }

    private Set<Author> getArticleAuthorSet(Article article) {
        List<Integer> authorIdList = articleAuthorDAO.searchByArticle(article);
        Set<Author> authorSet = new HashSet<Author>();
        if (authorIdList.size() > 0) {
            for (Integer authorId : authorIdList) {
                Author author = authorDAO.getAuthor(authorId);
                authorSet.add(author);
            }
        }
        return authorSet;
    }

    private List<Comment> getArticleCommentList(Article article) {
        return commentDAO.listCommentByArticleId(article.getId());
    }

    public Article getArticle(int id) {
        if (id > 0) {
            Article article = articleDAO.getArticleById(id);
            return fillArticleInfo(article);
        }
        else throw new InvalidIdException("Id should be more than 0");
    }

    public Article addArticle(Article article) {
        if (article == null) throw new InvalidArticleException("Article shouldn't be null");
        return articleDAO.create(article);
    }

    public void editArticle(Article articleToEdit) {
        if (articleToEdit == null) throw new InvalidArticleException("Article shouldn't be null");
        articleDAO.editArticle(articleToEdit);
    }

    public void deleteArticle(Article ... articles) {
        if (articles == null) throw new InvalidArticleException("Articles shouldn't be null");
        for (Article article : articles) {
            articleDAO.delete(article);
        }
    }

    public int totalNewsCount() {
        return articleDAO.getTotalCount();
    }

    public void attachAuthor(Author author, Article article) {
        if (author == null) throw new InvalidAuthorException("Author shouldn't be null");
        if (article == null) throw new InvalidArticleException("Article shouldn't be null");
        articleAuthorDAO.attachAuthor(author, article);
    }
}
