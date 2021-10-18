package ru.digitalleague.taxi_company.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.digitalleague.taxi_company.model.Order;
import ru.digitalleague.taxi_company.service.OrderService;
import ru.digitalleague.taxi_company.service.TaxiDriverService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

/**
 * Контроллер получающий информацию о поездке.
 */
@RestController
@Slf4j
public class TaxiController {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    OrderService orderService;

    @Autowired
    private TaxiDriverService taxiDriverService;

    /**
     * Метод получает инфо о начале поездки.
     * @param order
     * */

    @ApiOperation(value = "Контроллер устанавливающий время начала поездки")
    @PostMapping("/trip-start")
    public ResponseEntity<String> startTrip(@RequestBody Order order) {
        taxiDriverService.setBusy(order.getId(), false);
        log.info(String.format("Клиент №%d начал поездку.", order.getClientId()));
        orderService.updateOrderStartById(order.getId());
        return ResponseEntity.ok("Поездка началась!");
    }

    /**
     * Метод получает инфо о завершении поездки.
     * @param order
     * */

    @ApiOperation(value = "Контроллер устанавливающий время конца поездки")
    @PostMapping("/trip-complete")
    public ResponseEntity<String> completeTrip(@RequestBody Order order) {
        taxiDriverService.setBusy(order.getId(), true);
        log.info(String.format("Поездка №%d завершена", order.getId()));
        orderService.updateOrderEndById(order.getId());
        return ResponseEntity.ok("Услуга оказана!");
    }
}
