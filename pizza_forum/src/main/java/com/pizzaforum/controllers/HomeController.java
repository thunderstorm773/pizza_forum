package com.pizzaforum.controllers;

import com.pizzaforum.models.viewModels.CategoryView;
import com.pizzaforum.models.viewModels.TopicView;
import com.pizzaforum.services.api.CategoryService;
import com.pizzaforum.services.api.TopicService;
import com.pizzaforum.staticData.Constants;
import mvcFramework.annotations.controller.Controller;
import mvcFramework.annotations.parameters.PathVariable;
import mvcFramework.annotations.request.GetMapping;
import mvcFramework.model.Model;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
@Controller
public class HomeController {

    private TopicService topicService;

    private CategoryService categoryService;

    @Inject
    public HomeController(TopicService topicService,
                          CategoryService categoryService) {
        this.topicService = topicService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/home/topics";
    }

    @GetMapping("/home/topics")
    public String getHomePage(Model model) {
        List<TopicView> topicViews = this.topicService.findLatestTenTopics();
        model.addAttribute(Constants.TOPICS_KEY, topicViews);
        model.addAttribute(Constants.TITLE_KEY, Constants.ALL_TOPICS_TITLE_VALUE);
        model.addAttribute(Constants.VIEW_KEY, Constants.ALL_TOPICS_VIEW_VALUE);
        return "base-layout";
    }

    @GetMapping("/home/categories")
    public String getAllCategoriesPage(Model model) {
        List<CategoryView> categoryViews = this.categoryService.findAll();
        model.addAttribute(Constants.CATEGORIES_KEY, categoryViews);
        model.addAttribute(Constants.TITLE_KEY, Constants.ALL_CATEGORIES_HOME_TITLE_VALUE);
        model.addAttribute(Constants.VIEW_KEY, Constants.ALL_CATEGORIES_HOME_VIEW_VALUE);
        return "base-layout";
    }

    @GetMapping("/home/categories/{id}/topics")
    public String getAllTopicsForCategoryPage(@PathVariable("id") Long categoryId,
                                          Model model) {
        List<TopicView> topicViews = this.categoryService.findAllTopicsForCategory(categoryId);
        if (topicViews == null) {
            return "redirect:/home/categories";
        }

        model.addAttribute(Constants.TOPICS_KEY, topicViews);
        model.addAttribute(Constants.TITLE_KEY, Constants.ALL_TOPICS_FOR_CATEGORY_TITLE_VALUE);
        model.addAttribute(Constants.VIEW_KEY, Constants.ALL_TOPICS_FOR_CATEGORY_VIEW_VALUE);
        return "base-layout";
    }
}
