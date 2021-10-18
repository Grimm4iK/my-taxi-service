package ru.digitalleague.taxi_company.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
@ApiModel("Модель заказа")
public class Order {

    /**
     * Идентификатор поездки.
     */
    @ApiModelProperty("Идентификатор заказа")
    private Long id;

    /**
     * Идентификатор клиента.
     */
    @ApiModelProperty("Идентификатор клиента.")
    private Long clientId;

    /**
     * Дата, время начала поездки.
     */
    @ApiModelProperty("Дата, время начала поездки.")
    private Date start;

    /**
     * Дата, время окончания поездки.
     */
    @ApiModelProperty("Дата, время окончания поездки.")
    private Date end;

    /**
     * Идентификатор водителя.
     */
    @ApiModelProperty("Идентификатор водителя.")
    private Long driverId;

    /**
     * Цена поездки.
     */
    @ApiModelProperty("Цена поездки.")
    private Double amount;

    /**
     * Рейтинг водителя.
     */
    @ApiModelProperty("Рейтинг водителя.")
    private Integer rating;


}
