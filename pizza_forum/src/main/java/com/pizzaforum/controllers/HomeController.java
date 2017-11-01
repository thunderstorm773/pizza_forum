package com.pizzaforum.controllers;

import com.pizzaforum.models.viewModels.TopicView;
import com.pizzaforum.services.api.TopicService;
import com.pizzaforum.staticData.Constants;
import mvcFramework.annotations.controller.Controller;
import mvcFramework.annotations.request.GetMapping;
import mvcFramework.model.Model;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
@Controller
public class HomeController {

    private TopicService topicService;

    @Inject
    public HomeController(TopicService topicService) {
        this.topicService = topicService;
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
}
