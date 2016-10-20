package com.epam.newsmanagement.controller;

import com.epam.newsmanagement.entity.Tag;
import com.epam.newsmanagement.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Controller processes the requests for the tags
 */

@Controller
public class TagController {
    @Autowired
    TagService tagService;

    @RequestMapping(value = "/addTag", method = RequestMethod.GET)
    public String addTagPage(ModelMap model) {
        List<Tag> tagList = tagService.listTags();
        model.addAttribute("tagToAdd", new Tag());
        model.addAttribute("tagToUpdate", new Tag());
        model.addAttribute("tagList", tagList);
        return "addTag";
    }

    @RequestMapping (value = "/addTag", method = RequestMethod.POST)
    public String addTag(@RequestParam(value = "tagName", required = true)String tagName) {
        Tag tag = new Tag();
        tag.setName(tagName);
        tagService.createTag(tag);
        return "redirect:/NewsAdmin/main";
    }

    @RequestMapping (value = "/updateTag", method = RequestMethod.POST)
    public String updateTag(@RequestParam(value = "tagId", required = true) String tagId) {
        Tag tag = tagService.getTag(Integer.parseInt(tagId));
        tagService.updateTag(tag);
        return "redirect:/NewsAdmin/main";
    }

    @RequestMapping (value = "/deleteTag", method = RequestMethod.POST)
    public String deleteTag(@RequestParam(value = "tagId", required = true) String tagIdStr) {
        Tag tag = tagService.getTag(Integer.parseInt(tagIdStr));
        tagService.deleteTag(tag);
        return "redirect:/NewsAdmin/addTag";
    }
}
