package ru.digitalleague.taxi_company.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import ru.digitalleague.taxi_company.model.OrderModel;

import java.time.OffsetDateTime;

@Repository
@Mapper
public interface OrderMapper {

    /**
     * Сохранить заказ.
     *
     * @param orderModel заказ.
     */
    @Insert("insert into orders (id, client_id, driver_id, trip_start, trip_end, amount, rating)" +
            "values(#{id}, #{clientId}, #{driverId}, #{start}, #{end}, #{amount}, #{rating})")
    void saveOrder(OrderModel orderModel);

    /**
     * Установить время начала поездки.
     *
     * @param id номер заказа.
     */
    @Update("update orders set trip_start = now() where id = #{id}")
    void setStart(Long id);

    /**
     * Установить время начала поездки.
     *
     * @param id номер заказа.
     */
    @Update("update orders set trip_end = now() where id = #{id}")
    void setEnd(Long id);

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
    @Select("select id from orders where id = #{userId} and driver_id = #{driverId} order by id desc limit 1")
    Long findOrderByIds(Long userId, Long driverId);

    /**
     * Находит идентификатор водителя
     * @param id Идентификатор поездки
     * @return Идентификатор водителя
     */
    @Select("SELECT driver_id FROM orders WHERE id = #{id}")
    long findDriverIdByOrderId(long id);

    /**
     * Получить время начала поездки
     * @param orderId Идентификатор поездки
     * @return время начала поездки
     */
    @Select("SELECT trip_start FROM orders WHERE id = #{orderId}")
    OffsetDateTime findStartTripTimeById(Long orderId);

    /**
     * Получить время конца поездки
     * @param orderId Идентификатор поездки
     * @return время окончания поездки
     */
    @Select("SELECT trip_end FROM orders WHERE id = #{orderId}")
    OffsetDateTime findEndTripTimeById(long orderId);


    /**
     * Получить цену одной минуты поездки водителя
     * @param driverId Идентификатор водителя
     * @return Стоимость одной минуты
     */
    @Select("SELECT minute_cost FROM taxi_drive_info WHERE driver_id = #{driverId}")
    int findMinuteCostByDriverId(Long driverId);

}
