package ru.digitalleague.taxi_company.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitalleague.taxi_company.api.TaxiDriverService;
import ru.digitalleague.taxi_company.mapper.OrderMapper;
import ru.digitalleague.taxi_company.mapper.RatingTaxiDriverMapper;
import ru.digitalleague.taxi_company.mapper.TaxiDriverMapper;



@Service
@Slf4j
public class TaxiDriverServiceImpl implements TaxiDriverService {

    @Autowired
    private TaxiDriverMapper taxiDriverMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RatingTaxiDriverMapper ratingTaxiDriverMapper;


    @Override
    public Long getDriverIDByCriteria(String city, Integer level, String model) {
        return taxiDriverMapper.getDriverIDByCriteria(city, level, model);
    }

    /**
     * Устанавливает флаг занятости
     * @param id Идентификатор поездки
     * @param available флаг занятости
     */
    @Override
    public void setBusy(long id, boolean available){
        long driverId = orderMapper.findDriverIdByOrderId(id);
        taxiDriverMapper.setBusy(driverId, available);
    }

    /**
     * Сохраняет оценку поездки
     * @param orderId Идентификатор поездки
     * @param rating Оценка
     */
    @Override
    public void saveRatingTrip(long orderId, int rating) {
        long driverId = orderMapper.findDriverIdByOrderId(orderId);
        ratingTaxiDriverMapper.saveRatingTrip(orderId, driverId, rating);
        calcAvgRatingDriver(driverId);
    }

    /**
     * Считает средний рейтинг водителя
     * @param driverId Идентификатор водителя
     */
    private void calcAvgRatingDriver(long driverId){
        int gradeAvg = Math.round(ratingTaxiDriverMapper.findAverageGrade(driverId));
        taxiDriverMapper.saveAvgDriverRating(driverId, gradeAvg);
    }



}