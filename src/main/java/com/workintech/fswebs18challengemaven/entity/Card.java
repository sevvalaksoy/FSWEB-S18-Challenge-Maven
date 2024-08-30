package com.workintech.fswebs18challengemaven.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="value")
    private Integer value;

    @Enumerated(EnumType.STRING)
    private Type type;
    private Color color;

    public Card(){

    }
    public Card(Long id, Integer value, Color color){
        this.id = id;
        this.value = value;
        this.color =color;
    }
    public Card(Long id, Type type, Color color){
        this.id=id;
        this.type = type;
        this.color = type==Type.JOKER?null:color;
    }
    public void setId(Long id){
        this.id = id;
    }
    public void setValue(Integer value){
        if(this.type!=null){
            this.value = null;
        } else this.value = value;
    }
    public void setType(Type type){
        if(this.value!=null){
            this.type=null;
        } else this.type =type;
    }
    public void setColor(Color color){
        if(this.type==Type.JOKER){
            this.color=null;
        } else this.color = color;
    }

}
