package com.pizzaforum.controllers;

import com.pizzaforum.models.bindingModels.AddTopic;
import com.pizzaforum.models.viewModels.CategoryView;
import com.pizzaforum.models.viewModels.RegisteredUserView;
import com.pizzaforum.services.api.CategoryService;
import com.pizzaforum.services.api.TopicService;
import com.pizzaforum.staticData.Constants;
import com.pizzaforum.utils.ValidationUtil;
import mvcFramework.annotations.controller.Controller;
import mvcFramework.annotations.parameters.ModelAttribute;
import mvcFramework.annotations.request.GetMapping;
import mvcFramework.annotations.request.PostMapping;
import mvcFramework.model.Model;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.List;

@Stateless
@Controller
public class TopicController {

    private CategoryService categoryService;

    private TopicService topicService;

    @Inject
    public TopicController(CategoryService categoryService,
                           TopicService topicService) {
        this.categoryService = categoryService;
        this.topicService = topicService;
    }

    @GetMapping("/topics/new")
    public String getCreateTopicPage(Model model) {
        List<CategoryView> categoryViews = this.categoryService.findAll();
        model.addAttribute(Constants.CATEGORIES_KEY, categoryViews);
        model.addAttribute(Constants.TITLE_KEY, Constants.CREATE_TOPIC_TITLE_VALUE);
        model.addAttribute(Constants.VIEW_KEY, Constants.CREATE_TOPIC_VIEW_VALUE);
        return "base-layout";
    }

    @PostMapping("/topics/new")
    public String createTopic(@ModelAttribute AddTopic addTopic,
                              Model model,
                              HttpSession session) {
        ValidationUtil<AddTopic> validationUtil = new ValidationUtil<>(addTopic);
        List<String> errors = validationUtil.getInvalidParamsMessages();
        if (!errors.isEmpty()) {
            model.addAttribute(Constants.ERRORS_KEY, errors);
            List<CategoryView> categoryViews = this.categoryService.findAll();
            model.addAttribute(Constants.CATEGORIES_KEY, categoryViews);
            model.addAttribute(Constants.TITLE_KEY, Constants.CREATE_TOPIC_TITLE_VALUE);
            model.addAttribute(Constants.VIEW_KEY, Constants.CREATE_TOPIC_VIEW_VALUE);
            return "base-layout";
        }

        RegisteredUserView registeredUser = (RegisteredUserView) session.getAttribute(Constants.LOGGED_IN_USER_KEY);
        String username = registeredUser.getUsername();
        this.topicService.create(addTopic, username);
        return "redirect:/home/topics";
    }
}
