package com.spinapp.repositories;

import org.springframework.stereotype.Repository;

import com.spinapp.entities.QueryLog;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class LogRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void persistLog(QueryLog log) {
        entityManager.persist(log);
    }
}
