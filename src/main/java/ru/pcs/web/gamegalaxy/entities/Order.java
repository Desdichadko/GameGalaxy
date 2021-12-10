package ru.pcs.web.gamegalaxy.entities;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Getter
@EqualsAndHashCode(of = {"created", "client"})
@ToString(of = {"created", "address", "express", "status"})
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime created = LocalDateTime.now();

    @Column(length = 1, nullable = false)
    @Setter
    private Status status = Status.NEW;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, optional = false)
    private User user;

    @ElementCollection
    @Column(name = "quantity", nullable = false)
    @MapKeyJoinColumn(name = "item_id")
    private Map<Item, Integer> items = new HashMap<>();

    public Order(User user) {
        this.user = user;
    }

    public Map<Item, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }

    public void addItem(Item item) {
        items.merge(item, 1, (v1, v2) -> v1 + v2);
    }

    public void removeItem(Item item) {
        items.computeIfPresent(item, (k, v) -> v > 1 ? v - 1 : null);
    }
}
