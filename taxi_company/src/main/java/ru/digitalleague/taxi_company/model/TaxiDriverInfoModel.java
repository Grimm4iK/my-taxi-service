package ru.digitalleague.taxi_company.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class TaxiDriverInfoModel {

    @ApiModelProperty("ID водителя")
    private Long driverId;

    /**
     * Фамилия.
     */
    @ApiModelProperty("Фамилия водителя")
    private String lastName;

    /**
     * Имя.
     */
    @ApiModelProperty("Имя водителя")
    private String firstName;

    /**
     * Уровень.
     */
    @ApiModelProperty("Уровень водителя")
    private int level;

    /**
     * Модель авто.
     */
    @ApiModelProperty("Идентификатор автомобиля")
    private Long car;

    /**
     * Дата создания.
     */
    @ApiModelProperty("Дата регистрации водителя")
    private OffsetDateTime createDttm;

    /**
     * Стоимость одной минуты.
     */
    @ApiModelProperty("Стоимость одной минуты водителя")
    private int minuteCost;

    /**
     * id города.
     */
    @ApiModelProperty("Город водителя")
    private String city;

    /**
     * Индикатор занятости водителя (true - свободен).
     */
    @ApiModelProperty("Свободен ли водитель")
    private Boolean available;

    /**
     * Рейтинг водителя.
     */
    @ApiModelProperty("Рейтинг для водителя")
    private Double rating;

}
