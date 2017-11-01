package com.pizzaforum.repositories.impl;

import com.pizzaforum.entities.Topic;
import com.pizzaforum.repositories.api.TopicRepository;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Local(TopicRepository.class)
public class TopicRepositoryImpl implements TopicRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Topic topic) {
        this.entityManager.persist(topic);
    }

    @Override
    public List<Topic> findAll() {
        Query query = this.entityManager.createQuery("SELECT t FROM Topic AS t");
        List<Topic> topics = (List<Topic>) query.getResultList();
        return topics;
    }
}
