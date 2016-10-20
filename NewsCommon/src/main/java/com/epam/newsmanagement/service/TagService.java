package com.epam.newsmanagement.service;

import com.epam.newsmanagement.entity.Article;
import com.epam.newsmanagement.entity.Tag;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Natallia_Khadunai on 8/29/2016.
 */
public interface TagService {
    Tag createTag(Tag tag);
    Tag getTag(int id);
    List<Tag> listTags();
    void updateTag(Tag tag);
    void deleteTag(Tag tag);
    void attachTag(Tag tag, Article article);
    List<Article> searchByTags(Set<Tag> tagSet);
    Map<Tag, Integer> totalNewsCountForEachTag();
}
