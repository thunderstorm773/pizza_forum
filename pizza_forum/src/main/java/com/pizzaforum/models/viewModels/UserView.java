package com.pizzaforum.models.viewModels;

import java.util.List;

public class UserView {

    private String username;

    private List<TopicView> topics;

    public UserView() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<TopicView> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicView> topics) {
        this.topics = topics;
    }
}
