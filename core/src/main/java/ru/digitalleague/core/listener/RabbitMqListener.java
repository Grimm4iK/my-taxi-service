package ru.digitalleague.core.listener;

import lombok.extern.slf4j.Slf4j;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.digitalleague.core.mapper.TaxiInfoMapper;

@Component
@Slf4j
public class RabbitMqListener {
    @Autowired
    private TaxiInfoMapper taxiInfoMapper;
    /**
     * Получаем информацию о заказе.
     */
    @RabbitListener(queues = "trip-result")
    public void processRabbitMessage(String message) {
        log.info("Водитель №" + message + " свободен.");
        taxiInfoMapper.setFree(Long.parseLong(message));
    }
}