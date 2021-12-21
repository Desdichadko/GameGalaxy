package ru.pcs.web.gamegalaxy.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pcs.web.gamegalaxy.entities.Game;
import ru.pcs.web.gamegalaxy.entities.Order;
import ru.pcs.web.gamegalaxy.entities.Status;
import ru.pcs.web.gamegalaxy.repositories.CartRepository;
import ru.pcs.web.gamegalaxy.repositories.GamesRepository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Transactional
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final GamesRepository gamesRepository;
    private final AuthorizationServiceImpl authorizationService;


    public void addItem(Long gameId) {
        Order existingOrder = getActiveOrder();
        if (existingOrder != null) {
            existingOrder.addItem(gamesRepository.getById(gameId));
            recalculateTotalSum(existingOrder);
        } else {
            Order newOrder = new Order(authorizationService.getCurrentUser());
            newOrder.addItem(gamesRepository.getById(gameId));
            cartRepository.save(newOrder);
            recalculateTotalSum(newOrder);
        }
    }

    public Order getActiveOrder() {
        Order activeOrder = cartRepository.findFirstByStatusAndUserIs(Status.NEW, authorizationService.getCurrentUser());
        if (activeOrder == null) {
            activeOrder = new Order(authorizationService.getCurrentUser());
        }
        return activeOrder;
    }

    public Map<Game, Integer> getCurrentOrderItems() {
        try {
            return getActiveOrder().getItems();
        } catch (NullPointerException ignore) {
            Order newOrder = new Order(authorizationService.getCurrentUser());
            cartRepository.save(newOrder);
            return getActiveOrder().getItems();
        }
    }

    public void removeItemFromOrder(long id) {
        Order currentOrder = getActiveOrder();
        currentOrder.removeItem(gamesRepository.getById(id));
        recalculateTotalSum(currentOrder);
    }

    public void changeItemValueInOrder(long id, int quantity) {
        Order currentOrder = getActiveOrder();
        currentOrder.changeItemValue(gamesRepository.getById(id), quantity);
        recalculateTotalSum(currentOrder);
    }

    private void recalculateTotalSum(Order currentOrder) {
        currentOrder.calculateTotalSum();
        cartRepository.save(currentOrder);
    }

    public void payment() {
        Order currentOrder = getActiveOrder();
        currentOrder.setStatus(Status.PROCESSING);
        cartRepository.save(currentOrder);
    }
}
