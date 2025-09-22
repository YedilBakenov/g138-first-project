package kz.carproject.car.service;

import kz.carproject.car.dto.CarDto;
import kz.carproject.car.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {

     void addCar(Car car);

     void updateCar(Car car);

     Car getCarById(int id);

     List<Car> getAllCars();

     void deleteCar(int id);

     Car findCarColor(String color);

     List<Car> findCarsByText(String word);

     Page<Car> findAllPagination(Pageable pageable);

     Page<Car> findByCostGreaterThan(double cost, Pageable pageable);

     CarDto toDto(Car car);

     List<CarDto> toListDto(List<Car> cars);

}
