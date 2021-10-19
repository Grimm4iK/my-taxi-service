package ru.digitalleague.taxi_company.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface TaxiDriverMapper {

    @Results(id = "drivers", value = {
            @Result(property = "driverId", column = "driver_id"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "level", column = "level"),
            @Result(property = "createDttm", column = "create_dttm"),
            @Result(property = "minuteCost", column = "minute_cost"),
            @Result(property = "available", column = "available"),
            @Result(property = "rating", column = "rating"),
            @Result(property = "cityId", column = "city_id"),
            @Result(property = "carId", column = "car_id")
    })
    @Select("select driver_id from taxi_drive_info tdi left join city_queue q on q.city_id = tdi.city_id "+
            "left join car c on c.id = tdi.car_id where name = #{city} and level = #{level} and model = #{model} and available = true limit 1")
    Long getDriverIDByCriteria(String city, Integer level, String model);

    /**
     * Меняет флаг занятости водителя
     * @param driverId Идентификатор водителя
     * @param available Занят true/false
     */
    @Update("UPDATE taxi_drive_info SET available = #{available} WHERE driver_id = #{driverId}")
    void setBusy(long driverId, boolean available);

    /**
     * Установить водителю рейтинг
     *
     * @param driverId ID водителя
     * @param rating рейтинг для водителя
     */
    @Update("UPDATE taxi_drive_info SET rating = #{rating} WHERE  driver_id = #{driverId}")
    void saveAvgDriverRating(Long driverId, int rating);

}