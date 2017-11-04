package com.pizzaforum.services.impl;

import com.pizzaforum.entities.Category;
import com.pizzaforum.entities.Topic;
import com.pizzaforum.entities.User;
import com.pizzaforum.models.bindingModels.AddTopic;
import com.pizzaforum.models.bindingModels.EditTopic;
import com.pizzaforum.models.viewModels.EditTopicView;
import com.pizzaforum.models.viewModels.TopicDetailsView;
import com.pizzaforum.models.viewModels.TopicView;
import com.pizzaforum.repositories.api.CategoryRepository;
import com.pizzaforum.repositories.api.TopicRepository;
import com.pizzaforum.repositories.api.UserRepository;
import com.pizzaforum.services.api.TopicService;
import com.pizzaforum.staticData.Constants;
import com.pizzaforum.utils.MapperUtil;
import org.modelmapper.PropertyMap;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Local(TopicService.class)
public class TopicServiceImpl implements TopicService {

    private UserRepository userRepository;

    private CategoryRepository categoryRepository;

    private TopicRepository topicRepository;

    @Inject
    public TopicServiceImpl(UserRepository userRepository,
                            CategoryRepository categoryRepository,
                            TopicRepository topicRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.topicRepository = topicRepository;
    }

    @Override
    public void create(AddTopic addTopic, String username) {
        Topic topic = MapperUtil.getInstance().getModelMapper()
                .map(addTopic, Topic.class);
        User author = this.userRepository.findByUsername(username);
        Long categoryId = addTopic.getCategory();
        Category category = this.categoryRepository.findById(categoryId);
        topic.setAuthor(author);
        topic.setCategory(category);
        topic.setPublishDate(new Date());

        this.topicRepository.create(topic);
    }

    @Override
    public List<TopicView> findLatestTenTopics() {
        Comparator<Topic> dateComparator = (t1, t2) -> t2.getPublishDate().compareTo(t1.getPublishDate());
        List<Topic> topics = this.topicRepository.findAll()
                .stream().sorted(dateComparator)
                .limit(Constants.MAX_TOPICS_IN_HOME_PAGE).collect(Collectors.toList());
        PropertyMap<Topic, TopicView> propertyMap = new PropertyMap<Topic, TopicView>() {
            @Override
            protected void configure() {
                map().setRepliesCount(source.getReplies().size());
            }
        };

        List<TopicView> topicViews = MapperUtil.getInstance()
                .convertAll(topics, Topic.class, TopicView.class, propertyMap);
        return topicViews;
    }

    @Override
    public TopicDetailsView findTopicDetailsById(Long id) {
        Topic topic = this.topicRepository.findById(id);
        TopicDetailsView topicDetailsView = null;
        if (topic != null) {
            topicDetailsView = MapperUtil.getInstance().getModelMapper()
                    .map(topic, TopicDetailsView.class);
        }

        return topicDetailsView;
    }

    @Override
    public EditTopicView findById(Long id) {
        Topic topic = this.topicRepository.findById(id);
        EditTopicView editTopicView = null;
        if (topic != null) {
            editTopicView = MapperUtil.getInstance().getModelMapper()
                    .map(topic, EditTopicView.class);
        }

        return editTopicView;
    }

    @Override
    public void deleteById(Long id) {
        EditTopicView editTopicView = this.findById(id);
        if (editTopicView != null) {
            this.topicRepository.deleteById(id);
        }
    }

    @Override
    public void edit(EditTopic editTopic) {
        Topic topic = MapperUtil.getInstance().getModelMapper()
                .map(editTopic, Topic.class);
        Long categoryId = editTopic.getCategory();
        Category category = this.categoryRepository.findById(categoryId);
        topic.setCategory(category);
        this.topicRepository.edit(topic);
    }
}
