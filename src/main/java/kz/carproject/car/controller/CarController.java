package kz.carproject.car.controller;


import kz.carproject.car.db.DBConnector;
import kz.carproject.car.model.Car;
import kz.carproject.car.model.City;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class CarController {

    @GetMapping(value = "/") //https://localhost:8081/
    public String getHomePage(Model model){

        ArrayList<Car> cars = DBConnector.getAllCars();

        model.addAttribute("cars", cars);

        return "index";
    }

    @GetMapping(value = "/another") // https://localhost:8081/another
    public String anotherPage(){
        return "another";
    }

    @GetMapping(value = "/add-car")
    public String addCarPage(Model model){
        model.addAttribute("cities", DBConnector.getAllCities());
        return "add-car-page";
    }

    @PostMapping(value = "/add-car")
    public String addCar(Car car){
        DBConnector.addCar(car);
        return "redirect:/";
    }

    @GetMapping(value = "/get-car/{id}")
    public String getCarById(@PathVariable int id, Model model){

        model.addAttribute("car", DBConnector.getCarById(id));
        model.addAttribute("cities", DBConnector.getAllCities());

        return "details-car";
    }

    @GetMapping(value = "/get-car-2")
    public String getCarById2(@RequestParam int id,
                              Model model){
        model.addAttribute("car", Car.getCarById(id));

        return "details-car";
    }

    @PostMapping(value = "/update-car")
    public String updateCar(Car car){

        DBConnector.updateCar(car);

        return "redirect:/get-car/" + car.getId();
    }

    @PostMapping(value = "/delete-car")
    public String deleteCar(@RequestParam int id){
        DBConnector.deleteCar(id);
        return "redirect:/";
    }

}
