package ru.digitalleague.taxi_company.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.digitalleague.taxi_company.api.OrderService;
import ru.digitalleague.taxi_company.api.TaxiDriverService;
import ru.digitalleague.taxi_company.mapper.OrderMapper;
import ru.digitalleague.taxi_company.mapper.OrderTotalMapper;
import ru.digitalleague.taxi_company.model.OrderModel;
import ru.digitalleague.taxi_company.model.RatingModel;

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

    @Autowired
    OrderMapper orderMapper;

    /**
     * Метод получает инфо о начале поездки.
     * @param orderModel
     * */
    @ApiOperation(value = "Контроллер устанавливающий время начала поездки")
    @PostMapping("/trip-start")
    public ResponseEntity<String> startTrip(@RequestBody OrderModel orderModel) {
        taxiDriverService.setBusy(orderModel.getId(), false);
        log.info("Поездка началась!");
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
        amqpTemplate.convertAndSend("trip-result", "Номер поездки: = " + orderModel.getId()
                + " Цена поездки: = " + costTrip);
        return ResponseEntity.ok("Услуга оказана!");
    }

    /**
     * Метод устанавливает рейтинг водителя
     * @param orderId Идентификатор поездки
     * @param rating оценка от 1 до 5
     * */
    @GetMapping("/rate-trip")
    @ApiOperation(value = "Контроллер оценки поездки")
    public ResponseEntity<String> gradeTrip(@RequestHeader("order_id") long orderId,
                                            @RequestHeader("rating") int rating){
        if(rating < 1 || rating > 5) {
            return new ResponseEntity<>("Некорректная оценка. От 1 до 5.", HttpStatus.BAD_REQUEST);
        }
        taxiDriverService.saveRatingTrip(orderId, rating);
        return new ResponseEntity<>("Спасибо за отзыв", HttpStatus.OK);
    }
}
