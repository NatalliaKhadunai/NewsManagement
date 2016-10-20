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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.List;

/**
 * Controller processes the requests for the articles
 */
@Controller
public class ArticleController {
    @Autowired
    ArticleService articleService;
    @Autowired
    AuthorService authorService;
    @Autowired
    TagService tagService;

    @RequestMapping(value = "/addArticle", method = RequestMethod.GET)
    public String addArticlePage(ModelMap model) {
        model.addAttribute("article", new Article());
        List<Author> authorList = authorService.listAuthorsNotExpired();
        List<Tag> tagList = tagService.listTags();
        model.addAttribute("authorList", authorList);
        model.addAttribute("tagList", tagList);
        return "addUpdateArticle";
    }

    @RequestMapping (value = "/addArticle", method = RequestMethod.POST)
    public String addArticle(@ModelAttribute("article")Article article,
                           @RequestParam(value = "authorList", required = true) String[] authorList,
                           @RequestParam (value = "tagFilter", required = false) String[] tagList,
                           HttpServletResponse response,
                           ModelMap model) {
        article.setAuthorSet(new HashSet<Author>());
        for (String str : authorList) {
            int authorId = Integer.parseInt(str);
            article.getAuthorSet().add(authorService.getAuthor(authorId));
        }
        if (tagList.length != 0) {
            article.setTagSet(new HashSet<Tag>());
            for (String str : tagList) {
                int tagId = Integer.parseInt(str);
                article.getTagSet().add(tagService.getTag(tagId));
            }
        }
        article = articleService.addArticle(article);
        return "redirect:/NewsAdmin/newsPage?articleId=" + article.getId();
    }

    @RequestMapping (value = "/editArticle", method = RequestMethod.GET)
    public String editArticlePage(@RequestParam(value = "articleId", required = true) String articleIdStr,
                                  ModelMap model) {
        Article article = articleService.getArticle(Integer.parseInt(articleIdStr));
        model.addAttribute("article", article);
        List<Author> authorList = authorService.listAuthorsNotExpired();
        for (Author author : article.getAuthorSet()) {
            if (authorList.contains(author)) authorList.remove(author);
        }
        List<Tag> tagList = tagService.listTags();
        for (Tag tag : article.getTagSet()) {
            if (tagList.contains(tag)) tagList.remove(tag);
        }
        model.addAttribute("authorList", authorList);
        model.addAttribute("tagList", tagList);
        return "addUpdateArticle";
    }

    @RequestMapping (value = "/editArticle", method = RequestMethod.POST)
    public String editArticle(@ModelAttribute("article")Article article,
                              @RequestParam (value = "authorList", required = false) String[] authorIdArray,
                              @RequestParam (value = "tagList", required = false) String[] tagIdArray) {
        if (authorIdArray != null) {
            for (String str : authorIdArray) {
                int authorId = Integer.parseInt(str);
                Author author = authorService.getAuthor(authorId);
                articleService.attachAuthor(author, article);
            }
        }
        if (tagIdArray != null) {
            for (String str : tagIdArray) {
                int tagId = Integer.parseInt(str);
                Tag tag = tagService.getTag(tagId);
                tagService.attachTag(tag, article);
            }
        }
        articleService.editArticle(article);
        return "redirect:/NewsAdmin/main";
    }

    @RequestMapping (value = "/deleteArticle", method = RequestMethod.POST)
    public String deleteArticle(@RequestParam(value = "articleIds", required = true) String[] articleIdArray,
                                ModelMap model) {
        for (String articleIdStr : articleIdArray) {
            Article article = articleService.getArticle(Integer.parseInt(articleIdStr));
            articleService.deleteArticle(article);
        }
        return "redirect:/NewsAdmin/main";
    }

    @RequestMapping (value = "/newsPage", method = RequestMethod.GET)
    public String getNewsPage(@RequestParam(value = "articleId", required = true) String articleIdStr,
                              ModelMap model) {
        Article article = articleService.getArticle(Integer.parseInt(articleIdStr));
        model.addAttribute("article", article);
        return "articlePage";
    }
}
