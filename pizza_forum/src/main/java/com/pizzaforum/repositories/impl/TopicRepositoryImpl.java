package com.pizzaforum.repositories.impl;

import com.pizzaforum.entities.Topic;
import com.pizzaforum.repositories.api.TopicRepository;
import com.pizzaforum.staticData.Constants;

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

    @Override
    public Topic findById(Long id) {
        return this.entityManager.find(Topic.class, id);
    }

    @Override
    public void deleteById(Long id) {
        Topic topic = this.entityManager.find(Topic.class, id);
        this.entityManager.remove(topic);
    }

    @Override
    public void edit(Topic topic) {
        this.entityManager.merge(topic);
    }

    @Override
    public List<Topic> findLatestTenTopics() {
        Query query = this.entityManager
                .createQuery("SELECT t FROM Topic AS t ORDER BY t.publishDate DESC");
        List<Topic> topics = (List<Topic>) query.setMaxResults(Constants.MAX_TOPICS_IN_HOME_PAGE)
                .getResultList();
        return topics;
    }
}
