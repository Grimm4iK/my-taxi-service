package ru.digitalleague.taxi_company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitalleague.taxi_company.mapper.OrderMapper;
import ru.digitalleague.taxi_company.model.Order;

import java.time.OffsetDateTime;
import java.util.Date;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void saveOrder(Order order) {
        orderMapper.saveOrder(order);
    }

    @Override
    public void updateOrderStartById(Long id, OffsetDateTime start) {
        orderMapper.setStart(id, start);
    }

    @Override
    public void updateOrderEndById(Long id, OffsetDateTime end) {
        orderMapper.setEnd(id, end);
    }

    @Override
    public void createOrder(Long userId, Long driverId) {
        orderMapper.createOrder(userId, driverId);
    }

    @Override
    public Long findOrderByIds(Long userId, Long driverId) {
        return orderMapper.findOrderByIds(userId, driverId);
    }

    @Override
    public Long getDriverIDByCriteria(String city, Integer level, String model) {
        return orderMapper.getDriverIDByCriteria(city, level, model);
    }

    @Override
    public void setUnavailable(Long id) {
        orderMapper.setUnavailable(id);
    }
}
