package ru.pcs.web.gamegalaxy.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Getter
@EqualsAndHashCode(of = {"created", "user"})
@ToString(of = {"created", "status"})
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;

    @Column(nullable = false)
    private final LocalDateTime created = LocalDateTime.now();

    @Column(length = 1, nullable = false)
    @Setter
    private Status status = Status.NEW;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, optional = false)
    private User user;

    @ElementCollection
    @Column(name = "quantity", nullable = false)
    @MapKeyJoinColumn(name = "game_id")
    private Map<Game, Integer> items = new HashMap<>();

    @Setter
    @Column(name = "total_sum")
    private BigDecimal totalSum;

    public Order(User user) {
        this.user = user;
        this.totalSum = BigDecimal.ZERO;
    }

    /**
     * Calculates total sum of an active order
     */
    public void calculateTotalSum() {
        Map<Game, Integer> currentOrderItems = getItems();
        BigDecimal sum = new BigDecimal("0.");
        for (Map.Entry<Game, Integer> item : currentOrderItems.entrySet()) {
            sum = sum.add(item.getKey().getPrice().multiply(BigDecimal.valueOf(Long.parseLong(item.getValue().toString()))));
        }
        setTotalSum(sum);
    }

    public Map<Game, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }

    public void addItem(Game item) {
        items.merge(item, 1, (v1, v2) -> v1 + v2);
    }

    public void removeItem(Game item) {
        items.remove(item);
    }

    /**
     * Changes quantity of an item or add new one
     * @param item cart item Game
     * @param value quantity
     */
    public void changeItemValue(Game item, int value) {
        items.put(item, value);
    }
}
