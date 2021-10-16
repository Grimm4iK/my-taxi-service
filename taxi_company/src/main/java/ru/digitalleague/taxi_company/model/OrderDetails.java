package ru.digitalleague.taxi_company.model;

import lombok.Data;

@Data
public class OrderDetails {
    /**
     * Номер клиента.
     */
    private Long clientNumber;

    /**
     * Желаемый класс поездки (бизнес, эконом, и т.п.)
     */
    private int level;

    /**
     * Модель машины.
     */
    private String carModel;

    /**
     * Название города.
     */
    private String city;

}
