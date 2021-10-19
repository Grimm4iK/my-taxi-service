package ru.digitalleague.taxi_company.api;

import org.springframework.stereotype.Service;
import ru.digitalleague.taxi_company.model.OrderModel;

import java.time.OffsetDateTime;

@Service
public interface OrderService {
    void saveOrder(OrderModel orderModel);

    void updateOrderStartById(Long id);

    void updateOrderEndById(Long id);

    void createOrder(Long userId, Long driverId);

    Long findOrderByIds(Long userId, Long driverId);

    OffsetDateTime findStartTripTimeByOrderId(Long orderId);

    OffsetDateTime findEndTripTimeByOrderId(Long orderId);

    int getMinuteCost(Long orderId);

    Long calcTotalTripTime(Long orderId);

    int calcTotalTripSum(Long orderId);

    void saveTripSum(long orderId, int tripAmount);

}
