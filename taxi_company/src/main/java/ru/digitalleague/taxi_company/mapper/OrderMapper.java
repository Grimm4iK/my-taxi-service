package ru.digitalleague.taxi_company.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.digitalleague.taxi_company.model.Order;

import java.time.OffsetDateTime;
import java.util.Date;

@Repository
@Mapper
public interface OrderMapper {

    /**
     * Сохранить заказ.
     *
     * @param order заказ.
     */
    @Insert(" insert into orders (id, client_id, driver_id, trip_start, trip_end, amount, rating)" +
            " values(#{id}, #{clientId}, #{driverId}, #{start}, #{end}, #{amount}, #{rating})")
    void saveOrder(Order order);

    /**
     * Установить время начала поездки.
     *
     * @param id номер заказа.
     * @param start время начала
     */
    @Update("update orders set trip_start = #{start} where id = #{id}")
    void setStart(Long id, OffsetDateTime start);

    /**
     * Установить время начала поездки.
     *
     * @param id номер заказа.
     * @param end время начала
     */
    @Update("update orders set trip_end = #{end} where id = #{id}")
    void setEnd(Long id, OffsetDateTime end);

    /**
     * Создать заказ.
     *
     * @param userId ID клиента.
     * @param driverId ID водителя.
     */
    @Insert("insert into orders (client_id, driver_id) values (#{userId}, #{driverId})")
    void createOrder(Long userId, Long driverId);

    /**
     * Получить ордер ID по ID клиента и водителя.
     *
     * @param userId ID клиента.
     * @param driverId ID водителя.
     */
    @Select("select id from orders where client_id = #{userId} and driver_id = #{driverId} order by id desc limit 1")
    Long findOrderByIds(Long userId, Long driverId);

    @Select("select driver_id from taxi_drive_info tdi left join city_queue q on q.city_id = tdi.city_id "+
            "left join car c on c.id = tdi.car_id where name = #{city} and level = #{level} and model = #{model} and available = true limit 1")
    Long getDriverIDByCriteria(String city, Integer level, String model);

    @Update("update taxi_drive_info set available = false where driver_id = #{id}")
    void setUnavailable(Long id);

}
