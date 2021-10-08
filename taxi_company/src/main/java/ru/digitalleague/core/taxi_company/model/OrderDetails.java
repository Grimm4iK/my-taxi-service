package ru.digitalleague.core.taxi_company.model;

import lombok.Data;

@Data
public class OrderDetails {
    /**
     * Идентификатор клиента.
     */
    private Long clientNumber;

    /**
     * Желаемый класс поездки (бизнес, эконом, и т.п.)
     */
    private int level;

    /**
     * Должна быть enum.
     */
    private String carModel;

    /**
     * Название города.
     */
    private String city;

}
