package com.epam.newsmanagement.controller;

import com.epam.newsmanagement.entity.Author;
import com.epam.newsmanagement.service.AuthorService;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Controller processes the requests for the authors
 */

@Controller
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @RequestMapping(value = "/addAuthor", method = RequestMethod.GET)
    public String addAuthorPage(ModelMap model) {
        List<Author> authorList = authorService.listAuthorsNotExpired();
        model.addAttribute("authorToAdd", new Author());
        model.addAttribute("authorToUpdate", new Author());
        model.addAttribute("authorList", authorList);
        return "addUpdateAuthor";
    }

    @RequestMapping (value = "/addAuthor", method = RequestMethod.POST)
    public String addAuthor(@RequestParam(value = "authorName")String authorName) {
        List<String> resList = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher(authorName);
        while (matcher.find()) {
            resList.add(matcher.group());
        }
        String first_name = resList.get(0);
        String last_name = resList.get(1);
        Author author = new Author();
        author.setFirst_name(first_name);
        author.setLast_name(last_name);
        authorService.create(author);
        return "redirect:/NewsAdmin/main";
    }

    @RequestMapping (value = "/updateAuthor", method = RequestMethod.POST)
    public String updateAuthor(@RequestParam(value = "authorId", required = true)String authorId) {
        Author author = authorService.getAuthor(Integer.parseInt(authorId));
        authorService.update(author);
        return "redirect:/NewsAdmin/main";
    }

    @RequestMapping (value = "/getAuthor", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public String getAuthor(
            @RequestParam(value = "authorId", required = true) String authorId) {
        Author author = authorService.getAuthor(Integer.parseInt(authorId));
        JsonObject obj = new JsonObject();
        obj.addProperty("firstName", author.getFirst_name());
        obj.addProperty("lastName", author.getLast_name());
        return obj.toString();
    }

    @RequestMapping (value = "/expireAuthor", method = RequestMethod.POST)
    public String expireAuthor(
            @RequestParam(value = "authorId", required = true)String authorIdStr) {
        Author author = authorService.getAuthor(Integer.parseInt(authorIdStr));
        authorService.delete(author);
        return "redirect:/NewsAdmin/addAuthor";
    }
}
