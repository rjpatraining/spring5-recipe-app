package com.rjpard.spring5recipeapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String description;
    private BigDecimal amount;

    @ManyToOne
    private Recipe recipe;

    @OneToOne
    private UnitOfMeasure uom;

    public Ingredient(){

    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
        this.amount = amount;
        this.description = description;
        this.uom = uom;
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom, Recipe recipe) {
        this.amount = amount;
        this.description = description;
        this.recipe = recipe;
        this.uom = uom;
    }
}
