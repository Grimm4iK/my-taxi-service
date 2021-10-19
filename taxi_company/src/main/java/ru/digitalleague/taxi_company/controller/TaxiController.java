package ru.digitalleague.taxi_company.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.digitalleague.taxi_company.api.OrderService;
import ru.digitalleague.taxi_company.api.TaxiDriverService;
import ru.digitalleague.taxi_company.mapper.OrderTotalMapper;
import ru.digitalleague.taxi_company.model.OrderModel;

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

    @Autowired
    OrderTotalMapper orderTotalMapper;

    /**
     * Метод получает инфо о начале поездки.
     * @param orderModel
     * */

    @ApiOperation(value = "Контроллер устанавливающий время начала поездки")
    @PostMapping("/trip-start")
    public ResponseEntity<String> startTrip(@RequestBody OrderModel orderModel) {
        taxiDriverService.setBusy(orderModel.getId(), false);
        log.info(String.format("Клиент №%d начал поездку.", orderModel.getClientId()));
        orderService.updateOrderStartById(orderModel.getId());
        return ResponseEntity.ok("Поездка началась!");
    }

    /**
     * Метод получает инфо о завершении поездки.
     * @param orderModel
     * */

    @ApiOperation(value = "Контроллер устанавливающий время конца поездки")
    @PostMapping("/trip-complete")
    public ResponseEntity<String> completeTrip(@RequestBody OrderModel orderModel) {
        taxiDriverService.setBusy(orderModel.getId(), true);
        log.info(String.format("Поездка №%d завершена", orderModel.getId()));
        orderService.updateOrderEndById(orderModel.getId());
        int costTrip = orderService.calcTotalTripSum(orderModel.getId());
        orderService.saveTripSum(orderModel.getId(), costTrip);
        amqpTemplate.convertAndSend("trip-result", "Поездка завершена. " +
                "Номер поездки: = " + orderModel.getId() +
                " Цена поездки: = " + costTrip);
        return ResponseEntity.ok("Услуга оказана!");
    }
}
