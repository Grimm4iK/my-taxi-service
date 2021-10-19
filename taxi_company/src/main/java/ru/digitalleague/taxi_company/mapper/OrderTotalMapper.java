package ru.digitalleague.taxi_company.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OrderTotalMapper {

    /**
     * Сохранят номер заказа и сумму.
     *
     * @param orderId заказ.
     * @param tripSum заказ.
     */
    @Insert("insert into order_total (order_id, sum) values (#{orderId}, #{tripSum})")
    void saveTotalOrderSumById(long orderId, int tripSum);
}
