package com.pizzaforum.services.api;

import com.pizzaforum.models.bindingModels.AddReply;

public interface ReplyService {

    void create(AddReply addReply, String authorUsername, Long topicId);
}
