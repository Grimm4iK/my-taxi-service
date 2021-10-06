package ru.digitalleague.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.digitalleague.core.mapper.CarInfoMapper;
import ru.digitalleague.core.model.CarDetails;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarInfoServiceImpl implements CarInfoService{

    private final CarInfoMapper carInfoMapper;

    @Override
    public CarDetails getCarModelById(CarDetails carDetails) {
        return carInfoMapper.getCarModelById(carDetails.getCarId());
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public int insertCar(CarDetails carDetails) {
        return carInfoMapper.insertCar(carDetails);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public int updateCarByPrimaryKey(CarDetails carDetails) {
        return carInfoMapper.updateCarByPrimaryKey(carDetails);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public int deleteCarByPrimaryKey(CarDetails carDetails) {
        return carInfoMapper.deleteCarByPrimaryKey(carDetails.getCarId());
    }

    @Override
    public int getCarCount() {
        return carInfoMapper.getCarCount();
    }

    @Override
    public List<CarDetails> getAllCarModels() {
        return carInfoMapper.getAllCarModels();
    }
}