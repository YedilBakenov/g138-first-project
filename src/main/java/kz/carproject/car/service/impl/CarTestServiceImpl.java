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

import java.util.List;
import java.util.NoSuchElementException;

@Service("test")
@RequiredArgsConstructor
public class CarTestServiceImpl implements CarService {

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
        return carRepository.findAll(Sort.by(Sort.Order.desc("id")));
    }

    @Override
    public void deleteCar(int id) {
        carRepository.deleteById(id);
    }

    @Override
    public Car findCarColor(String color) {
        return null;
    }

    @Override
    public List<Car> findCarsByText(String text) {
        return List.of();
    }

    @Override
    public Page<Car> findAllPagination(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Car> findByCostGreaterThan(double cost, Pageable pageable) {
        return null;
    }

    @Override
    public CarDto toDto(Car car) {
        return null;
    }

    @Override
    public List<CarDto> toListDto(List<Car> cars) {
        return List.of();
    }
}
