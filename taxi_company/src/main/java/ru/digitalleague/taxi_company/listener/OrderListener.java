package ru.digitalleague.taxi_company.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.digitalleague.core.model.OrderDetails;
import ru.digitalleague.taxi_company.api.OrderService;
import ru.digitalleague.taxi_company.api.TaxiDriverService;

import java.io.IOException;

@Slf4j
@Component
public class OrderListener {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private OrderService orderService;
    @Autowired
    private TaxiDriverService taxiDriverService;

    @RabbitListener(queues = "${application.broker.receive-queue}")
    public void onMessage(Message message) throws IOException {
        log.info("Получено сообщение из rabbitmq: " + message);
        OrderDetails orderDetails = objectMapper.readValue(message.getBody(), OrderDetails.class);
        Long driverId = taxiDriverService.getDriverIDByCriteria(orderDetails.getCity(), orderDetails.getLevel(), orderDetails.getCarModel());
        orderService.createOrder(orderDetails.getClientNumber(), driverId);
        Long orderId = orderService.findOrderByIds(orderDetails.getClientNumber(), driverId);
        log.info(String.format("Заказ №%d, клиент №%d, водитель №%d", orderId, orderDetails.getClientNumber(), driverId));
    }
}
