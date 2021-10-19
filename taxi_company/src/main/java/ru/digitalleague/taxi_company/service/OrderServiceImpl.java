package ru.digitalleague.taxi_company.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.digitalleague.taxi_company.api.OrderService;
import ru.digitalleague.taxi_company.mapper.OrderMapper;
import ru.digitalleague.taxi_company.mapper.OrderTotalMapper;
import ru.digitalleague.taxi_company.model.OrderModel;

import java.time.Duration;
import java.time.OffsetDateTime;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderTotalMapper orderTotalMapper;

    @Override
    @Transactional
    public void saveOrder(OrderModel orderModel) {
        orderMapper.saveOrder(orderModel);
    }

    @Override
    public void updateOrderStartById(Long id) {
        orderMapper.setStart(id);
    }

    @Override
    public void updateOrderEndById(Long id) {
        orderMapper.setEnd(id);
    }

    @Override
    @Transactional
    public void createOrder(Long userId, Long driverId) {
        orderMapper.createOrder(userId, driverId);
    }

    @Override
    public Long findOrderByIds(Long userId, Long driverId) {
        return orderMapper.findOrderByIds(userId, driverId);
    }

    @Override
    public OffsetDateTime findStartTripTimeByOrderId(Long orderId){
        OffsetDateTime date = orderMapper.findStartTripTimeById(orderId);
        return date;
    }

    @Override
    public OffsetDateTime findEndTripTimeByOrderId(Long orderId){
        OffsetDateTime date = orderMapper.findEndTripTimeById(orderId);
        return date;
    }

    @Override
    public int getMinuteCost(Long orderId) {
        Long driverId = orderMapper.findDriverIdByOrderId(orderId);
        int minuteCost = orderMapper.findMinuteCostByDriverId(driverId);
        return minuteCost;
    }

    @Override
    public Long calcTotalTripTime(Long orderId) {
        OffsetDateTime startTripTime = findStartTripTimeByOrderId(orderId);
        OffsetDateTime endTripTime = findEndTripTimeByOrderId(orderId);
        Duration duration = Duration.between(startTripTime,endTripTime);
        long timeTripInMinutes = duration.toMinutes();
        log.info("Длительность поездки составила {} минут", timeTripInMinutes);
        return timeTripInMinutes;
    }

    @Override
    public int calcTotalTripSum(Long orderId) {
        long tripDuration = calcTotalTripTime(orderId);
        int minuteCost = getMinuteCost(orderId);
        int totalTripSum = (int) (tripDuration * minuteCost);
        log.info("Итоговая стоимость составила {} рублей", totalTripSum);
        return totalTripSum;
    }

    @Override
    public void saveTripSum(long orderId, int tripAmount) {
        orderTotalMapper.saveTotalOrderSumById(orderId, tripAmount);
    }

}




