package com.pizzaforum.repositories.api;

import com.pizzaforum.entities.Topic;

import java.util.List;

public interface TopicRepository {

    void create(Topic topic);

    List<Topic> findAll();

    Topic findById(Long id);

    void deleteById(Long id);

    void edit(Topic topic);
}
