package ru.digitalleague.taxi_company.api;

import ru.digitalleague.taxi_company.model.Order;

/**
 * Сервис обработки заказов.
 * */
public interface OrderService {

    void save(Order order);
}
