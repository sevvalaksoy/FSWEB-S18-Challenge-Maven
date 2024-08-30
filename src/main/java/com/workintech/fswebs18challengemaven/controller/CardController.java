package com.workintech.fswebs18challengemaven.controller;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.repository.CardRepository;
import com.workintech.fswebs18challengemaven.util.CardValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {
    private CardRepository cardRepository;

    @Autowired
    public CardController(CardRepository cardRepository){
        this.cardRepository = cardRepository;
    }

    @GetMapping
    public List<Card> returnCards(){
        return cardRepository.findAll();
    }

    @GetMapping("/byColor/{color}")
    public List<Card> returnCard(@PathVariable String color){
        CardValidation.checkColorExistence(cardRepository,color);
        return cardRepository.findByColor(color);
    }

    @PostMapping
    public Card post(@RequestBody Card card){
        CardValidation.doesExists(cardRepository, card.getId(), false);
        cardRepository.save(card);
        return card;
    }

    @PutMapping("/{id}")
    public Card update(@PathVariable Long id){
        CardValidation.isIdValid(id);
        Card card = cardRepository.findById(id);
        return cardRepository.update(card);
    }

    @PutMapping("/")
    public Card update(@RequestBody Card card){
        return cardRepository.update(card);
    }

    @DeleteMapping("/{id}")
    public Card delete(@PathVariable Long id){
        CardValidation.isIdValid(id);
        Card card = cardRepository.findById(id);
        cardRepository.remove(id);
        return card;
    }

    @GetMapping("/byValue/{value}")
    public List<Card> returnByValue(@PathVariable Integer value){
        return cardRepository.findByValue(value);
    }

    @GetMapping("/byType/{type}")
    public List<Card> returnByType(@PathVariable String type){
        return cardRepository.findByType(type);
    }
}
