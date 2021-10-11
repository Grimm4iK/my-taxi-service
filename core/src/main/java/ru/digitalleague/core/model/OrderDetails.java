package ru.digitalleague.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("Модель заказа такси")
public class OrderDetails {

    /**
     * Идентификатор клиента.
     */
    @ApiModelProperty("Идентификатор клиента")
    private Long clientNumber;

    /**
     * Желаемый класс поездки (бизнес, эконом, и т.п.)
     */
    @ApiModelProperty("Уровень авто")
    private int level;

    /**
     * Должна быть enum.
     */
    @ApiModelProperty("Марка машины")
    private String carModel;

    /**
     * Название города.
     */
    @ApiModelProperty("Город")
    private String city;

}
