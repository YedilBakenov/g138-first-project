package kz.carproject.car.controller;


import kz.carproject.car.model.Car;
import kz.carproject.car.repository.CarRepositoryCustom;
import kz.carproject.car.repository.impl.CarRepositoryCustomImpl;
import kz.carproject.car.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/rest") //http://localhost:8081/api/rest
@RequiredArgsConstructor
public class CarRestController {

    @Autowired
    @Qualifier("main")
    private CarService carService;

    private final CarRepositoryCustom carRepositoryCustom;

    @GetMapping
    public List<Car> getMain(){
        return carService.getAllCars();
    }

    @GetMapping(value = "/get-car/{id}")
    public Car getCarById(@PathVariable int id){
        return carService.getCarById(id);
    }

    @PutMapping(value = "/update-car")
    public void updateCar(@RequestBody Car car){
        carService.updateCar(car);
    }

    @PostMapping(value = "/add-car")
    public Car addCar(@RequestBody Car car){
        carService.addCar(car);
        return car;
    }

    @DeleteMapping(value = "/delete-car/{id}")
    public void deleteCar(@PathVariable int id){
        carService.deleteCar(id);
    }

    @GetMapping(value = "/greater-cost")
    public List<Car> getCars(@RequestParam double cost){

        return carRepositoryCustom.findCarsMoreCost(cost);
    }

    @GetMapping(value = "/name-or-cost")
    public List<Car> getCars(@RequestParam(required = false) String modelName,
                             @RequestParam(required = false) Double cost){

        return carRepositoryCustom.findCarsByModelNameOrCost(modelName, cost);
    }

    @GetMapping(value = "/sorted-cost")
    public List<Car> sortedCars(){
        return carRepositoryCustom.sortCarsByCost();
    }



}
