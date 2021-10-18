package ru.digitalleague.taxi_company.service;

public interface TaxiDriverService {

    Long getDriverIDByCriteria(String city, Integer level, String model);

    void setBusy(long id, boolean available);

}