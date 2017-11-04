package com.pizzaforum.services.api;

import com.pizzaforum.models.bindingModels.AddTopic;
import com.pizzaforum.models.bindingModels.EditTopic;
import com.pizzaforum.models.viewModels.EditTopicView;
import com.pizzaforum.models.viewModels.TopicDetailsView;
import com.pizzaforum.models.viewModels.TopicView;

import java.util.List;

public interface TopicService {

    void create(AddTopic addTopic, String username);

    List<TopicView> findLatestTenTopics();

    TopicDetailsView findTopicDetailsById(Long id);

    EditTopicView findById(Long id);

    void deleteById(Long id);

    void edit(EditTopic editTopic);
}
