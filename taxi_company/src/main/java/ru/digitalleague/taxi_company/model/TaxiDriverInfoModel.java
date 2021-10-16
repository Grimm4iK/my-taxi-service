package ru.digitalleague.taxi_company.model;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class TaxiDriverInfoModel {

    private Long driverId;

    /**
     * Фамилия.
     */
    private String lastName;

    /**
     * Имя.
     */
    private String firstName;

    /**
     * Уровень.
     */
    private int level;

    /**
     * Модель авто.
     */
    private Long car;

    /**
     * Дата создания.
     */
    private OffsetDateTime createDttm;

    private String city;
    private Boolean available;
    private Double rating;

}
