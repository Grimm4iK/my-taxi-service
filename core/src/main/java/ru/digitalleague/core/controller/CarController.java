package ru.digitalleague.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.digitalleague.core.model.CarDetails;
import ru.digitalleague.core.service.CarInfoService;

import java.util.List;

@RestController
public class CarController {

    @Autowired
    private CarInfoService carInfoService;

    @PostMapping("/ins")
    public void insertCar(@RequestBody CarDetails carDetails){
        carInfoService.insertCar(carDetails);
    }

    @PostMapping("/upd")
    public void updateCar(@RequestBody CarDetails carDetails){
        carInfoService.updateCarByPrimaryKey(carDetails);
    }

    @PostMapping("/del")
    public void deleteCar(@RequestBody CarDetails carDetails){
        carInfoService.deleteCarByPrimaryKey(carDetails);
    }

    @GetMapping("/get")
    public CarDetails getCarModel(@RequestBody CarDetails carDetails){
        return carInfoService.getCarModelById(carDetails);
    }

    @GetMapping("/getAll")
    public List<CarDetails> getAllCars(){
        return carInfoService.getAllCarModels();
    }

    @GetMapping("/count")
    public int getCarCount(){
        return carInfoService.getCarCount();
    }
}