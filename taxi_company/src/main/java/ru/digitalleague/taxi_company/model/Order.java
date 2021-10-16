package ru.digitalleague.taxi_company.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class Order {
    private Long id;
    private Long clientId;
    private Date start;
    private Date end;
    private Long driverId;
    private Double amount;
    private Integer rating;
}
