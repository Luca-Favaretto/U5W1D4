package com.epicode.U5D2.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Setter
@NoArgsConstructor
public abstract class Item {
    protected int calories;
    protected double price;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public Item(int calories, double price) {
        this.calories = calories;
        this.price = price;
    }

}
