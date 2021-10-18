package ru.digitalleague.taxi_company.service;

import org.springframework.stereotype.Service;
import ru.digitalleague.taxi_company.model.Order;

import java.time.OffsetDateTime;
import java.util.Date;

@Service
public interface OrderService {
    void saveOrder(Order order);

    void updateOrderStartById(Long id);

    void updateOrderEndById(Long id);

    void createOrder(Long userId, Long driverId);

    Long findOrderByIds(Long userId, Long driverId);

}
