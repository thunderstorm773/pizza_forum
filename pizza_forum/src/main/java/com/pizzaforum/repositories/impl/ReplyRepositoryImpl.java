package com.pizzaforum.repositories.impl;

import com.pizzaforum.entities.Reply;
import com.pizzaforum.repositories.api.ReplyRepository;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Local(ReplyRepository.class)
public class ReplyRepositoryImpl implements ReplyRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Reply reply) {
        this.entityManager.persist(reply);
    }
}
