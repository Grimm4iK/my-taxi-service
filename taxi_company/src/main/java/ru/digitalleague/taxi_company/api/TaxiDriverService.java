package ru.digitalleague.taxi_company.api;

public interface TaxiDriverService {

    Long getDriverIDByCriteria(String city, Integer level, String model);

    void setBusy(long id, boolean available);

    void saveRatingTrip(long orderId, int rating);

}