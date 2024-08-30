package com.workintech.fswebs18challengemaven.repository;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.util.CardValidation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CardRepositoryImpl implements CardRepository{
    private EntityManager entityManager;

    @Autowired
    public CardRepositoryImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public Card save(Card card) {
        CardValidation.exists(entityManager, card.getId(), false);
        entityManager.persist(card);
        return card;
    }

    @Override
    public Card findById(Long id) {
        CardValidation.exists(entityManager, id, true);
        CardValidation.isIdValid(id);
        return entityManager.find(Card.class, id);
    }

    @Override
    public List<Card> findByColor(String color) {
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c " +
                "WHERE b.color=:color", Card.class);
        query.setParameter("color", color);
        CardValidation.checkColorExistence(query);
        return query.getResultList();
    }

    @Override
    public List<Card> findAll() {
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM" +
                "Card c", Card.class);
        return query.getResultList();
    }

    @Override
    public List<Card> findByValue(Integer value) {
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c" +
                "WHERE c.value=:value", Card.class);
        query.setParameter("value", value);
        return query.getResultList();
    }

    @Override
    public List<Card> findByType(String type) {
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card" +
                "WHERE c.type=:type", Card.class);
        query.setParameter("type", type);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Card update(Card card) {
        return entityManager.merge(card);
    }

    @Transactional
    @Override
    public Card remove(Long id) {
        CardValidation.isIdValid(id);
        Card card = findById(id);
        entityManager.remove(card);
        return card;
    }

}
