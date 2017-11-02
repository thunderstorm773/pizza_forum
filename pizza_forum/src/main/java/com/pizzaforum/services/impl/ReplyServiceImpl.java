package com.pizzaforum.services.impl;

import com.pizzaforum.entities.Reply;
import com.pizzaforum.entities.Topic;
import com.pizzaforum.entities.User;
import com.pizzaforum.models.bindingModels.AddReply;
import com.pizzaforum.repositories.api.ReplyRepository;
import com.pizzaforum.repositories.api.TopicRepository;
import com.pizzaforum.repositories.api.UserRepository;
import com.pizzaforum.services.api.ReplyService;
import com.pizzaforum.utils.MapperUtil;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Date;

@Stateless
@Local(ReplyService.class)
public class ReplyServiceImpl implements ReplyService {

    private ReplyRepository replyRepository;

    private UserRepository userRepository;

    private TopicRepository topicRepository;

    @Inject
    public ReplyServiceImpl(ReplyRepository replyRepository,
                            UserRepository userRepository,
                            TopicRepository topicRepository) {
        this.replyRepository = replyRepository;
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
    }

    @Override
    public void create(AddReply addReply, String authorUsername, Long topicId) {
        Reply reply = MapperUtil.getInstance().getModelMapper()
                .map(addReply, Reply.class);
        User author = this.userRepository.findByUsername(authorUsername);
        Topic topic = this.topicRepository.findById(topicId);
        reply.setAuthor(author);
        reply.setPublishDate(new Date());
        reply.setTopic(topic);
        this.replyRepository.create(reply);
    }
}
