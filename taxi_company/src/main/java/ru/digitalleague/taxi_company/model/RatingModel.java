package ru.digitalleague.taxi_company.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("Модель рейтинга водителя")
public class RatingModel {

    /**
     * Идентификатор водителя.
     */
    @ApiModelProperty("Идентификатор водителя")
    private Long driverId;

    /**
     * Рейтинг водителя.
     */
    @ApiModelProperty("Полученная оценка водителя")
    private int driverRating;

    @ApiModelProperty("Главная оценка водителя")
    private int totalRating;
}
