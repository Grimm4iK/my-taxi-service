package ru.digitalleague.core.service;

import ru.digitalleague.core.model.CarDetails;
import ru.digitalleague.core.model.TaxiDriverInfoModel;

import java.util.List;

public interface CarInfoService {

    CarDetails getCarModelById(CarDetails carDetails);

    int insertCar(CarDetails carDetails);

    int updateCarByPrimaryKey(CarDetails carDetails);

    int deleteCarByPrimaryKey(CarDetails carDetails);

    int getCarCount();

    List<CarDetails> getAllCarModels();
}