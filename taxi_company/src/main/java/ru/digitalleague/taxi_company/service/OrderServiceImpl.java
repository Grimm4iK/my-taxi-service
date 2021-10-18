package ru.digitalleague.taxi_company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.digitalleague.taxi_company.mapper.OrderMapper;
import ru.digitalleague.taxi_company.model.Order;

import java.time.OffsetDateTime;
import java.util.Date;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderService orderService;

    @Override
    @Transactional
    public void saveOrder(Order order) {
        orderMapper.saveOrder(order);
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

}




