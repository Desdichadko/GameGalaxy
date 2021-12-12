/*
package ru.pcs.web.gamegalaxy.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@EqualsAndHashCode(of = "name")
@ToString(of = {"name", "price"})
@NoArgsConstructor
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    private BigDecimal price;

    public Item(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }
}*/
