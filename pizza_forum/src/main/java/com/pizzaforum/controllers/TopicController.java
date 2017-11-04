package com.pizzaforum.controllers;

import com.pizzaforum.models.bindingModels.AddReply;
import com.pizzaforum.models.bindingModels.AddTopic;
import com.pizzaforum.models.bindingModels.EditTopic;
import com.pizzaforum.models.viewModels.CategoryView;
import com.pizzaforum.models.viewModels.EditTopicView;
import com.pizzaforum.models.viewModels.RegisteredUserView;
import com.pizzaforum.models.viewModels.TopicDetailsView;
import com.pizzaforum.services.api.CategoryService;
import com.pizzaforum.services.api.ReplyService;
import com.pizzaforum.services.api.TopicService;
import com.pizzaforum.staticData.Constants;
import com.pizzaforum.utils.ValidationUtil;
import mvcFramework.annotations.controller.Controller;
import mvcFramework.annotations.parameters.ModelAttribute;
import mvcFramework.annotations.parameters.PathVariable;
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

    private ReplyService replyService;

    @Inject
    public TopicController(CategoryService categoryService,
                           TopicService topicService,
                           ReplyService replyService) {
        this.categoryService = categoryService;
        this.topicService = topicService;
        this.replyService = replyService;
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

    @GetMapping("/topics/details/{id}")
    public String getTopicDetailsPage(@PathVariable("id") Long topicId,
                                      Model model) {
        TopicDetailsView topicDetailsView = this.topicService.findTopicDetailsById(topicId);
        if (topicDetailsView == null) {
            return "redirect:/home/topics";
        }

        model.addAttribute(Constants.TOPIC_KEY, topicDetailsView);
        model.addAttribute(Constants.TITLE_KEY, Constants.TOPIC_DETAILS_TITLE_VALUE);
        model.addAttribute(Constants.VIEW_KEY, Constants.TOPIC_DETAILS_VIEW_VALUE);
        return "base-layout";
    }

    @PostMapping("/topics/details/{id}")
    public String addReply(@ModelAttribute AddReply addReply,
                           @PathVariable("id") Long topicId,
                           Model model,
                           HttpSession session) {
        TopicDetailsView topicDetailsView = this.topicService.findTopicDetailsById(topicId);
        if (topicDetailsView == null) {
            return "redirect:/home/topics";
        }

        ValidationUtil<AddReply> validationUtil = new ValidationUtil<>(addReply);
        List<String> errors = validationUtil.getInvalidParamsMessages();
        if (!errors.isEmpty()) {
            model.addAttribute(Constants.ERRORS_KEY, errors);
            model.addAttribute(Constants.TOPIC_KEY, topicDetailsView);
            model.addAttribute(Constants.TITLE_KEY, Constants.TOPIC_DETAILS_TITLE_VALUE);
            model.addAttribute(Constants.VIEW_KEY, Constants.TOPIC_DETAILS_VIEW_VALUE);
            return "base-layout";
        }

        RegisteredUserView registeredUserView = (RegisteredUserView) session.getAttribute(Constants.LOGGED_IN_USER_KEY);
        String username = registeredUserView.getUsername();
        this.replyService.create(addReply, username, topicId);
        return "redirect:/topics/details/" + topicId;
    }

    @GetMapping("/topics/delete/{id}")
    public String getDeleteTopicPage(@PathVariable("id") Long topicId,
                                     Model model) {
        EditTopicView topicView = this.topicService.findById(topicId);
        if (topicView == null) {
            return "redirect:/home/topics";
        }

        model.addAttribute(Constants.TOPIC_KEY, topicView);
        model.addAttribute(Constants.TITLE_KEY, Constants.DELETE_TOPIC_TITLE_VALUE);
        model.addAttribute(Constants.VIEW_KEY, Constants.DELETE_TOPIC_VIEW_VALUE);
        return "base-layout";
    }

    @PostMapping("/topics/delete/{id}")
    public String deleteTopic(@PathVariable("id") Long topicId) {
        this.topicService.deleteById(topicId);
        return "redirect:/home/topics";
    }

    @GetMapping("/topics/edit/{id}")
    public String getEditTopicPage(@PathVariable("id") Long topicId,
                                   Model model) {
        EditTopicView topicView = this.topicService.findById(topicId);
        if (topicView == null) {
            return "redirect:/home/topics";
        }

        List<CategoryView> categoryViews = this.categoryService.findAll();
        model.addAttribute(Constants.CATEGORIES_KEY, categoryViews);
        model.addAttribute(Constants.TOPIC_KEY, topicView);
        model.addAttribute(Constants.TITLE_KEY, Constants.EDIT_TOPIC_TITLE_VALUE);
        model.addAttribute(Constants.VIEW_KEY, Constants.EDIT_TOPIC_VIEW_VALUE);
        return "base-layout";
    }

    @PostMapping("/topics/edit/{id}")
    public String editTopic(@PathVariable("id") Long topicId,
                            @ModelAttribute EditTopic editTopic,
                            Model model) {
        EditTopicView topicView = this.topicService.findById(topicId);
        if (topicView == null) {
            return "redirect:/home/topics";
        }

        ValidationUtil<EditTopic> validationUtil = new ValidationUtil<>(editTopic);
        List<String> errors = validationUtil.getInvalidParamsMessages();
        if (!errors.isEmpty()) {
            model.addAttribute(Constants.ERRORS_KEY, errors);
            List<CategoryView> categoryViews = this.categoryService.findAll();
            model.addAttribute(Constants.CATEGORIES_KEY, categoryViews);
            model.addAttribute(Constants.TITLE_KEY, Constants.EDIT_TOPIC_TITLE_VALUE);
            model.addAttribute(Constants.VIEW_KEY, Constants.EDIT_TOPIC_VIEW_VALUE);
            return "base-layout";
        }

        editTopic.setId(topicId);
        this.topicService.edit(editTopic);
        return "redirect:/topics/details/" + topicId;
    }
}
