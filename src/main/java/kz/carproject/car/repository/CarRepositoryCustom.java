package kz.carproject.car.repository;

import kz.carproject.car.model.Car;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepositoryCustom {

    List<Car> findCarsMoreCost(double cost);

    List<Car> findCarsByModelNameOrCost(String modelName, Double cost);

    List<Car> sortCarsByCost();

}
