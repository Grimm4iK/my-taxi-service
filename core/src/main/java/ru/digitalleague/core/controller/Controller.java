package ru.digitalleague.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.digitalleague.core.model.OrderDetails;

@RestController
public class Controller {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/order")
    public void addNewOrderDetails(@RequestBody OrderDetails orderDetails) {
        try {
            template.convertAndSend("order", mapper.writeValueAsString(orderDetails));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
