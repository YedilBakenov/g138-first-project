package kz.carproject.car.controller;


import kz.carproject.car.model.Car;
import kz.carproject.car.model.City;
import kz.carproject.car.repository.CityRepository;
import kz.carproject.car.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CarController {  //http://localhost:8081/

    private final CityRepository cityRepository;

    @Autowired
    @Qualifier("main")
    private CarService carService;

    @GetMapping(value = "/") //https://localhost:8081/
    public String getHomePage(Model model){

        ArrayList<Car> cars = (ArrayList<Car>)carService.getAllCars();

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
        carService.addCar(car);
        return "redirect:/";
    }

    @PostMapping(value = "/add-city")
    public String addCity(@RequestParam(name = "car_id") int car_id,
                          @RequestParam(name = "city_id") int city_id){

        Car car = carService.getCarById(car_id);
        City city = cityRepository.findById(city_id).get();

        car.getCities().add(city);

        carService.addCar(car);

        System.out.println("ID CITY" + city_id);

        return "redirect:/get-car/" + car_id;
    }

    @PostMapping(value = "/delete-city")
    public String deleteCity(@RequestParam int car_id,
                          @RequestParam int city_id){

        Car car = carService.getCarById(car_id);
        City city = cityRepository.findById(city_id).get();

        car.getCities().remove(city);

        carService.addCar(car);

        return "redirect:/get-car/" + car_id;
    }

    @GetMapping(value = "/get-car/{id}")
    public String getCarById(@PathVariable int id, Model model){

        model.addAttribute("car", carService.getCarById(id));

        Car car = carService.getCarById(id);
        List<City> cities = cityRepository.findAll();
        cities.removeAll(car.getCities());

        model.addAttribute("cities", cities);


//        model.addAttribute("cities", dbConnector.getAllCities());

        return "details-car";
    }

    @GetMapping(value = "/get-car-2")
    public String getCarById2(@RequestParam int id,
                              Model model){
        model.addAttribute("car", carService.getCarById(id));

        return "details-car";
    }

    @GetMapping(value = "/search")
    public String getCarsByText(@RequestParam("word") String word,
                              Model model){
        model.addAttribute("cars", carService.findCarsByText(word));

        return "index";
    }

    @PostMapping(value = "/update-car")
    public String updateCar(Car car){

        carService.updateCar(car);

        return "redirect:/get-car/" + car.getId();
    }

    @PostMapping(value = "/delete-car")
    public String deleteCar(@RequestParam int id){
        carService.deleteCar(id);
        return "redirect:/";
    }

    @GetMapping(value = "/find-color")
    public String findCArByColor(@RequestParam String color,
                                 Model model){
        Car car = carService.findCarColor(color);
        model.addAttribute("car",car);

        System.out.println(car.getColor());
        System.out.println(car.getModelName());

        return "details-by-color";
    }

}
