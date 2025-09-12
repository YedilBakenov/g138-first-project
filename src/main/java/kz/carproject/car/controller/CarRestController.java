package kz.carproject.car.controller;


import kz.carproject.car.model.Car;
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



}
