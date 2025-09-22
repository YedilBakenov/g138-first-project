package kz.carproject.car.service.impl;

import kz.carproject.car.dto.CarDto;
import kz.carproject.car.model.Car;
import kz.carproject.car.repository.CarRepository;
import kz.carproject.car.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service("main")
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public void addCar(Car car) {
        carRepository.save(car);
    }

    @Override
    public void updateCar(Car car) {
        carRepository.save(car);
    }

    @Override
    public Car getCarById(int id) {
        return carRepository.findById(id).orElseThrow(()-> {
            return new NoSuchElementException("Not Found Car!");
        });
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll(Sort.by(Sort.Order.asc("id")));
    }

    @Override
    public void deleteCar(int id) {
        carRepository.deleteById(id);
    }

    @Override
    public Car findCarColor(String color) {
        return carRepository.findCarByColor(color);
    }

    @Override
    public List<Car> findCarsByText(String word) {
        return carRepository.searchCarsByWord(word);
    }

    @Override
    public Page<Car> findAllPagination(Pageable pageable) {
        return carRepository.findAll(pageable);
    }

    @Override
    public Page<Car> findByCostGreaterThan(double cost, Pageable pageable) {
        return carRepository.findByCostGreaterThan(cost, pageable);
    }

    @Override
    public CarDto toDto(Car car) {

        if(car==null){
            return null;
        }

        CarDto carDto = new CarDto();

        carDto.setId(car.getId());
        carDto.setCarPassport(car.getCarPassport());
        carDto.setEngine(car.getEngine());
        carDto.setColor(car.getColor());
        carDto.setCena(car.getCost());
        carDto.setCities(car.getCities());
        carDto.setName(car.getModelName());
        carDto.setOwner(car.getOwner());

        return carDto;
    }

    @Override
    public List<CarDto> toListDto(List<Car> cars) {

        List<CarDto> carDtoList = new ArrayList<>();

        for(Car car: cars){
            carDtoList.add(toDto(car));
        }
        return carDtoList;
    }


}
