package com.pizzaforum.services.api;

import com.pizzaforum.models.bindingModels.AddTopic;
import com.pizzaforum.models.viewModels.TopicDetailsView;
import com.pizzaforum.models.viewModels.TopicView;

import java.util.List;

public interface TopicService {

    void create(AddTopic addTopic, String username);

    List<TopicView> findLatestTenTopics();

    TopicDetailsView findById(Long id);
}
