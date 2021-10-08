package ru.digitalleague.core;

import lombok.extern.slf4j.Slf4j;

import java.time.OffsetDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.digitalleague.core.model.TaxiDriverInfoModel;
import ru.digitalleague.core.service.TaxiInfoService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class CoreApplicationTests {

    @Autowired
    private TaxiInfoService taxiInfoService;

    TaxiDriverInfoModel taxiDriverInfoModel = TaxiDriverInfoModel.builder()
            .carModel("BMW")
            .lastName("Иванов")
            .firstName("Иван")
            .level(0)
            .createDttm(OffsetDateTime.now())
            .build();

    @Test
    void insert() {
        //GIVEN

        //WHEN
        int insert = taxiInfoService.insert(taxiDriverInfoModel);

        //THAN
        assertThat(insert).isEqualTo(1);
    }

    @Test
    void update_twoTransactionalSeparateSelect() throws InterruptedException {

        //GIVEN

        //WHEN
        taxiInfoService.insert(taxiDriverInfoModel);
        Long driverId = taxiDriverInfoModel.getDriverId();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                TaxiDriverInfoModel taxiDriverInfoModel1 = taxiInfoService.selectByPrimaryKey(driverId);
                taxiDriverInfoModel1.setLevel(taxiDriverInfoModel1.getLevel() + 1);
                taxiInfoService.updateByPrimaryKey(taxiDriverInfoModel1);
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                TaxiDriverInfoModel taxiDriverInfoModel2 = taxiInfoService.selectByPrimaryKey(driverId);
                taxiDriverInfoModel2.setLevel(taxiDriverInfoModel2.getLevel() + 1);
                taxiInfoService.updateByPrimaryKey(taxiDriverInfoModel2);
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        //THEN
    }

    @Test
    void update_twoTransactionalInOneSelect() throws InterruptedException {

        taxiInfoService.insert(taxiDriverInfoModel);
        Long driverId = taxiDriverInfoModel.getDriverId();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                taxiInfoService.getByIdAndUpdateLevel(driverId);
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                taxiInfoService.getByIdAndUpdateLevel(driverId);
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }

//    @SneakyThrows
//    @Test
//    void update_repeatableRead() {
//
//        taxiInfoService.insert(taxiDriverInfoModel);
//
//        Thread thread1 = new Thread(() -> {
//            System.out.println("666: " + taxiInfoService.twoSelectByIdWithSleep());
//        });
//        Thread thread2 = new Thread(() -> {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            taxiInfoService.insert(taxiDriverInfoModel);
//            log.debug("Добавлена запись");
//        });
//        thread1.start();
//        thread2.start();
//        thread1.join();
//        thread2.join();
//
//    }

}
