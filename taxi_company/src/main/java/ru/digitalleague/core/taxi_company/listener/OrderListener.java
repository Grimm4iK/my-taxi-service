package ru.digitalleague.core.taxi_company.listener;

import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

@Slf4j
public class OrderListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        log.info("Received message from rabbitmq " + message);
    }
}
