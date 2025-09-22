package kz.carproject.car.controller;


import kz.carproject.car.dto.CarDto;
import kz.carproject.car.mapper.CarMapper;
import kz.carproject.car.model.Car;
import kz.carproject.car.repository.CarRepositoryCustom;
import kz.carproject.car.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private final CarMapper carMapper;

    @GetMapping
    public List<CarDto> getMain(){
        return carMapper.carDtoList(carService.getAllCars());
    }

    @GetMapping(value = "/get-car/{id}")
    public CarDto getCarById(@PathVariable int id){

        return carMapper.toDto(carService.getCarById(id));
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
    public List<CarDto> getCars(@RequestParam double cost){

        return  carMapper.carDtoList(carRepositoryCustom.findCarsMoreCost(cost));
    }

    @GetMapping(value = "/name-or-cost")
    public List<CarDto> getCars(@RequestParam(required = false) String modelName,
                             @RequestParam(required = false) Double cost){

        return carMapper.carDtoList(carRepositoryCustom.findCarsByModelNameOrCost(modelName, cost));
    }

    @GetMapping(value = "/sorted-cost")
    public List<Car> sortedCars(){
        return carRepositoryCustom.sortCarsByCost();
    }

    @GetMapping(value = "/pagination")
    public Page<Car> getAllCars(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "2") int size,
                                @RequestParam(defaultValue = "id") String param,
                                @RequestParam(defaultValue = "asc") String direction){

        Sort sort = direction.equalsIgnoreCase("desc")? Sort.by(param).descending()
                : Sort.by(param).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return carService.findAllPagination(pageable);
    }

    @GetMapping(value = "/pagination-cost")
    public Page<Car> getAllCarsCostPagination(@RequestParam double cost,
                                              @RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "2") int size,
                                              @RequestParam(defaultValue = "cost") String param,
                                              @RequestParam(defaultValue = "asc") String direction){

        Sort sort = direction.equalsIgnoreCase("desc")? Sort.by(param).descending()
                : Sort.by(param).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return carService.findByCostGreaterThan(cost, pageable);
    }



}
