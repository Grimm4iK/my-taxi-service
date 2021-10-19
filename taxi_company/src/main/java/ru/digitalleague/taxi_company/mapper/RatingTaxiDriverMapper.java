package ru.digitalleague.taxi_company.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RatingTaxiDriverMapper {

    /**
     * Записывает оценку за поездку
     * @param driverId Идентификатор водителя
     * @param rating Оценка
     * @param orderId Идентификатор поездки
     */
    @Insert("INSERT INTO driver_rating (order_id, driver_id, rating) VALUES (#{orderId}, #{driverId}, #{rating})")
    void saveRatingTrip(long orderId, long driverId, int rating);

    @Select("SELECT AVG(rating) FROM driver_rating WHERE driver_id = #{driverId}")
    float findAverageGrade(long driverId);

}
