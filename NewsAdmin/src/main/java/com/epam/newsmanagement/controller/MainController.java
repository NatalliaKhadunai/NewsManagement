package com.epam.newsmanagement.controller;

import com.epam.newsmanagement.entity.Article;
import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.entity.Tag;
import com.epam.newsmanagement.service.ArticleService;
import com.epam.newsmanagement.service.AuthorService;
import com.epam.newsmanagement.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.epam.newsmanagement.util.Filter;
import com.epam.newsmanagement.util.SortMode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Controller processes the requests for the main page
 */

@Controller
public class MainController {
    @Autowired
    ArticleService articleService;
    @Autowired
    AuthorService authorService;
    @Autowired
    TagService tagService;
    public static final int RECORD_PER_PAGE = 3;
    private SortMode currentSortMode = SortMode.NONE;
    private Filter filter = null;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String mainPage(@RequestParam(value = "page", required = false) String pageParam,
                           @RequestParam(value = "sortMode", required = false) String sortMode,
                           @RequestParam(value = "authorFilter", required = false) String authorIdStr,
                           @RequestParam(value = "tagFilter", required = false) String[] tagIdStrArray,
                           ModelMap model) {
        List<Author> authorList = authorService.listAuthors();
        List<Tag> tagList = tagService.listTags();

        int page;
        if (pageParam != null && sortMode == null && authorIdStr == null && tagIdStrArray == null)
            page = Integer.parseInt(pageParam);
        else page = 1;
        List<Article> articleList;
        int maxPageNo;
        if (authorIdStr != null || tagIdStrArray != null) {
            articleList = getArticleFilteredList(authorIdStr, tagIdStrArray, page);
            filter = new Filter();
            filter.setAuthorIdStr(authorIdStr);
            filter.setTagIdArray(tagIdStrArray);
            maxPageNo = (int) Math.ceil((double) articleList.size() / (double) RECORD_PER_PAGE);
        }
        else if (filter != null) {
            articleList = getArticleFilteredList(new String(filter.getAuthorIdStr()),
                    filter.getTagIdArray(), page);
            maxPageNo = (int) Math.ceil((double) articleList.size() / (double) RECORD_PER_PAGE);
        }
        else {
            articleList = getArticleSortedList(sortMode, page);
            maxPageNo = (int) Math.ceil((double) articleService.totalNewsCount() / (double) RECORD_PER_PAGE);
        }

        model.addAttribute("authorList", authorList);
        model.addAttribute("tagList", tagList);
        model.addAttribute("articleList", articleList);
        model.addAttribute("page", page);
        model.addAttribute("maxPageNo", maxPageNo);
        return "main";
    }

    @RequestMapping (value = "/resetFilter", method = RequestMethod.GET)
    public String resetFilter() {
        filter = null;
        return "redirect:/NewsAdmin/main";
    }

    private List<Article> getArticleSortedList(String sortMode, int page) {
        List<Article> articleList = null;
        if (sortMode != null) currentSortMode = SortMode.valueOf(sortMode.toUpperCase());
        switch (currentSortMode) {
            case SORT_BY_DATE: {
                articleList = articleService.getArticlesSortByDate(page, RECORD_PER_PAGE);
            }
            break;
            case SORT_BY_NUM_OF_COMMENTS: {
                articleList = articleService.getArticlesSortByNumOfComments(page, RECORD_PER_PAGE);
            }
            break;
            case NONE: {
                articleList = articleService.getArticles(page, RECORD_PER_PAGE);
            }
            break;
        }
        return articleList;
    }


    private List<Article> getArticleFilteredList(String authorIdStr, String[] tagIdStrArray, int page) {
        Set<Tag> tagSet = new HashSet<Tag>();
        for (String tagIdStr : tagIdStrArray) {
            tagSet.add(tagService.getTag(Integer.parseInt(tagIdStr)));
        }
        Author author = authorService.getAuthor(Integer.parseInt(authorIdStr));

        List<Article> articleList1 = articleService.getArticlesByAuthor(author);
        List<Article> articleList2 = tagService.searchByTags(tagSet);
        List<Article> articleIntersectionList = new ArrayList<Article>();
        for (Article article1 : articleList1) {
            if (articleList2.contains(article1)) articleIntersectionList.add(article1);
        }

        List<Article> articleResultList;
        if (articleIntersectionList.size() > RECORD_PER_PAGE) articleResultList = articleIntersectionList.subList((page - 1) * RECORD_PER_PAGE  + 1, page * RECORD_PER_PAGE + 1);
        else articleResultList = articleIntersectionList;

        return articleResultList;
    }
}