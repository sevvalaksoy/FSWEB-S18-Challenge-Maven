package com.workintech.fswebs18challengemaven.util;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.exceptions.CardException;
import com.workintech.fswebs18challengemaven.repository.CardRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.http.HttpStatus;

public class CardValidation {
    public static void isIdValid(Long id) {
        if(id == null || id < 0 ){
            throw new CardException("Id is not valid", HttpStatus.BAD_REQUEST);
        }
    }

    public static void doesExists(CardRepository cardRepository, Long id, boolean existence){
        if(existence){
            if(cardRepository.findById(id)==null){
                throw new CardException("The card does not exist", HttpStatus.NOT_FOUND);
            }
        } else {
            if(cardRepository.findById(id)!=null){
                throw new CardException("The card already exists.", HttpStatus.BAD_REQUEST);
            }
        }
    }
    public static void exists(EntityManager entityManager, Long id, boolean existence){
        if(existence){
            if(entityManager.find(Card.class,id)==null){
                throw new CardException("Record does not exists", HttpStatus.NOT_FOUND);
            }
        } else {
            if(entityManager.find(Card.class,id)!=null){
                throw new CardException("Record does not exists", HttpStatus.BAD_REQUEST);
            }
        }
    }
    public static void checkColorExistence(CardRepository cardRepository, String color){
        if(cardRepository.findByColor(color)==null){
            throw new CardException("There are no cards with the given color", HttpStatus.NOT_FOUND);
        }
    }
    public static void checkColorExistence(TypedQuery<Card> query){
        if(query.getResultList().isEmpty()){
            throw new CardException("There are no cards with the given color", HttpStatus.NOT_FOUND);
        }
    }
}
