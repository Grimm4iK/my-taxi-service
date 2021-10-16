package ru.digitalleague.taxi_company.service;

import org.springframework.stereotype.Service;
import ru.digitalleague.taxi_company.model.Order;

import java.time.OffsetDateTime;
import java.util.Date;

@Service
public interface OrderService {
    void saveOrder(Order order);

    void updateOrderStartById(Long id, OffsetDateTime start);

    void updateOrderEndById(Long id, OffsetDateTime end);

    void createOrder(Long userId, Long driverId);

    Long findOrderByIds(Long userId, Long driverId);

    Long getDriverIDByCriteria(String city, Integer level, String model);

    void setUnavailable(Long id);

}
