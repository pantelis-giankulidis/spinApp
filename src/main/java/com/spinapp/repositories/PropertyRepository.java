package com.spinapp.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.spinapp.entities.Property;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class PropertyRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Iterable<Property> findAll() {
        TypedQuery<Property> query = entityManager.createQuery("SELECT p FROM Property p", Property.class);
        return query.getResultList();
    }

    public Iterable<Property> executeSearchQuery(String stringQuery) {
        TypedQuery<Property> query = entityManager.createQuery(stringQuery, Property.class);
        return query.getResultList();
    }

}
