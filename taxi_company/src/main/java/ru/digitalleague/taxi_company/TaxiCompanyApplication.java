package ru.digitalleague.taxi_company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import ru.digitalleague.taxi_company.config.ApplicationConfiguration;

@SpringBootApplication
@Import(ApplicationConfiguration.class)
public class TaxiCompanyApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaxiCompanyApplication.class, args);
    }

}
