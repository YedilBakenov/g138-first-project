package kz.carproject.car.controller;


import kz.carproject.car.model.Car;
import kz.carproject.car.model.City;
import kz.carproject.car.repository.CarRepository;
import kz.carproject.car.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
public class CarController {

//    private final DBConnector dbConnector;
    private final CarRepository carRepository;
    private final CityRepository cityRepository;

    @GetMapping(value = "/") //https://localhost:8081/
    public String getHomePage(Model model){

        ArrayList<Car> cars = (ArrayList<Car>)carRepository.findAll(Sort.by(Sort.Order.asc("id")));

        model.addAttribute("cars", cars);

        return "index";
    }

    @GetMapping(value = "/another") // https://localhost:8081/another
    public String anotherPage(){
        return "another";
    }

    @GetMapping(value = "/add-car")
    public String addCarPage(Model model){
//        model.addAttribute("cities", dbConnector.getAllCities());
        return "add-car-page";
    }

    @PostMapping(value = "/add-car")
    public String addCar(Car car){
        carRepository.save(car);
        return "redirect:/";
    }

    @PostMapping(value = "/add-city")
    public String addCity(@RequestParam int car_id,
                          @RequestParam int city_id){

        Car car = carRepository.findById(car_id).get();
        City city = cityRepository.findById(city_id).get();

        car.getCities().add(city);

        carRepository.save(car);

        return "redirect:/get-car/" + car_id;
    }

    @PostMapping(value = "/delete-city")
    public String deleteCity(@RequestParam int car_id,
                          @RequestParam int city_id){

        Car car = carRepository.findById(car_id).get();
        City city = cityRepository.findById(city_id).get();

        car.getCities().remove(city);

        carRepository.save(car);

        return "redirect:/get-car/" + car_id;
    }

    @GetMapping(value = "/get-car/{id}")
    public String getCarById(@PathVariable int id, Model model){

        model.addAttribute("car", carRepository.findById(id).orElseThrow(()-> {
            return new NoSuchElementException("Not Found Car!");
        }));

        Car car = carRepository.findById(id).get();
        List<City> cities = cityRepository.findAll();
        cities.removeAll(car.getCities());

        model.addAttribute("cities", cities);


//        model.addAttribute("cities", dbConnector.getAllCities());

        return "details-car";
    }

    @GetMapping(value = "/get-car-2")
    public String getCarById2(@RequestParam int id,
                              Model model){
        model.addAttribute("car", carRepository.findById(id).orElseThrow());

        return "details-car";
    }

    @PostMapping(value = "/update-car")
    public String updateCar(Car car){

        carRepository.save(car);

        return "redirect:/get-car/" + car.getId();
    }

    @PostMapping(value = "/delete-car")
    public String deleteCar(@RequestParam int id){
        carRepository.deleteById(id);
        return "redirect:/";
    }

}
