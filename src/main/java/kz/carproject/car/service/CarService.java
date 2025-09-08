package kz.carproject.car.service;

import kz.carproject.car.model.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {

     void addCar(Car car);

     void updateCar(Car car);

     Car getCarById(int id);

     List<Car> getAllCars();

     void deleteCar(int id);


}
