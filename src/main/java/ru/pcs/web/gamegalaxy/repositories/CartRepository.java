package ru.pcs.web.gamegalaxy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pcs.web.gamegalaxy.entities.Order;
import ru.pcs.web.gamegalaxy.entities.Status;
import ru.pcs.web.gamegalaxy.entities.User;

import java.util.List;

public interface CartRepository extends JpaRepository <Order, Long>{

    Order findFirstByStatusAndUserIs(Status status, User user);

}
