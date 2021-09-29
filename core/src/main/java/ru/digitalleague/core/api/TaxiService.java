package ru.digitalleague.core.api;

import ru.digitalleague.core.model.OrderDetails;

/**
 * Сервис отправки информации о заказе.
 */
public interface TaxiService {

    /**
     * Информируем такси о поступлении заказа.
     */
    String notifyTaxi(OrderDetails orderDetails);

}
