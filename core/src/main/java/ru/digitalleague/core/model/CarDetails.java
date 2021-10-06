package ru.digitalleague.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDetails {


    private long carId;

    private CarModel carModel;

    private OffsetDateTime createDttm;
}
