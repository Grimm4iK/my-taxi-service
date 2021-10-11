package ru.digitalleague.taxi_company.model;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class Order {

    /**
     * Идентификатор поездки.
     */
    private Long orderId;

    /**
     * Идентификатор клиента.
     */
    private Long clientNumber;

    /**
     * Идентификатор водителя.
     */
    private Long driverId;

    /**
     * Дата, время начала поездки.
     */
    private OffsetDateTime startTrip;

    /**
     * Дата, время окончания поездки.
     */
    private OffsetDateTime endTrip;

}
