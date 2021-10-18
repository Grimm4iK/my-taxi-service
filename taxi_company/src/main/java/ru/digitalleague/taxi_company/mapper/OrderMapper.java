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
    @Insert("insert into orders (id, client_id, driver_id, trip_start, trip_end, amount, rating)" +
            "values(#{id}, #{clientId}, #{driverId}, #{start}, #{end}, #{amount}, #{rating})")
    void saveOrder(Order order);

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
     * Получить последний оред ID.
     */
    @Select("select last_value from order_seq")
    Long lastOrderId();

    /**
     * Сохранить сумму заказа
     *  @param orderId ID заказа.
     *  @param orderSum сумма заказа.
     */
    @Insert("INSERT INTO order_total (order_id, sum) SELECT #{orderId}, #{orderSum} WHERE NOT EXISTS (SELECT order_id FROM order_total WHERE order_id = #{orderId})")
    void saveTotalOrderSum(Long orderId, Long orderSum);

    /**
     * Получить время начала поездки
     *  @param orderId ID заказа.
     */
    @Select("select start_trip from orders where id = #{orderId}")
    OffsetDateTime getTripStartTimeByOrderId(Long orderId);

    /**
     * Получить время окончания поездки
     *  @param orderId ID заказа.
     */
    @Select("select end_trip from orders where id = #{orderId}")
    OffsetDateTime getTripEndTimeByOrderId(Long orderId);

    /**
     * Находит идентификатор водителя
     * @param id Идентификатор поездки
     * @return Идентификатор водителя
     */
    @Select("SELECT driver_id FROM orders WHERE id = #{id}")
    long findDriverIdByOrderId(long id);

}
