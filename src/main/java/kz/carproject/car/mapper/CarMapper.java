package kz.carproject.car.mapper;

import kz.carproject.car.dto.CarDto;
import kz.carproject.car.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface CarMapper {

    @Mapping(source = "cost", target = "cena")
    @Mapping(source = "modelName", target = "name")
    CarDto toDto(Car car);

    @Mapping(source = "cena", target = "cost")
    @Mapping(source = "name", target = "modelName")
    Car toModel(CarDto carDto);

    List<CarDto> carDtoList(List<Car> cars);
    List<Car> carModelList(List<CarDto> carDtoList);
}
