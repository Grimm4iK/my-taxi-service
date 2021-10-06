package ru.digitalleague.core.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import ru.digitalleague.core.model.CarDetails;
import java.util.List;

@Repository
@Mapper
public interface CarInfoMapper {

    @Select("select count(1) from taxidb.taxi_service.car")
    int getCarCount();

    @Results(id = "carsInfo", value = {
            @Result(property = "carId", column = "id"),
            @Result(property = "carModel", column = "model"),
            @Result(property = "createDttm", column = "createdttm")
    })
    @Select("select * from taxidb.taxi_service.car")
    List<CarDetails> getAllCarModels();

    @Results(id = "getCarModelById", value = {
            @Result(property = "carId", column = "id"),
            @Result(property = "carModel", column = "model"),
            @Result(property = "createDttm", column = "createdttm")
    })
    @Select("select * from taxidb.taxiservice.car where id=#{carId}")
    CarDetails getCarModelById(Long carId);

    @Insert("INSERT INTO taxidb.taxi_service.car (id, model) VALUES (nextval('taxidb.taxi_service.car_seq'), #{carModel})")
    int insertCar(CarDetails carDetails);

    @Update("UPDATE taxidb.taxi_service.car SET model = #{carModel} WHERE id = #{carId}")
    int updateCarByPrimaryKey(CarDetails carDetails);

    @Delete("DELETE FROM taxidb.taxi_service.car WHERE id = #{carId}")
    int deleteCarByPrimaryKey(Long carId);
}