package com.epam.newsmanagement.dao;

import com.epam.newsmanagement.entity.Article;
import com.epam.newsmanagement.entity.Tag;

import java.util.List;
import java.util.Set;

/**
 * Created by Natallia_Khadunai on 8/26/2016.
 */
public interface ArticleTagDAO extends DAO {
    void attachTag(int articleId, int tagId);
    List<Integer> searchByArticle(Article article);
    List<Integer> searchByTags(Set<Tag> tagSet);
    List<Integer> searchByTag(Tag tag);
}
