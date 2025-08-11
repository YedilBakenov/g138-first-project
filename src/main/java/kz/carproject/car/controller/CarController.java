package kz.carproject.car.controller;


import kz.carproject.car.model.Car;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class CarController {

    @GetMapping(value = "/") //https://localhost:8081/
    public String getHomePage(Model model){

        ArrayList<Car> cars = Car.getCars();

        model.addAttribute("cars", cars);

        return "index";
    }

    @GetMapping(value = "/another") // https://localhost:8081/another
    public String anotherPage(){
        return "another";
    }

    @GetMapping(value = "/add-car")
    public String addCarPage(){
        return "add-car-page";
    }

    @PostMapping(value = "/add-car")
    public String addCar(Car car){
        Car.addCar(car);
        return "redirect:/";
    }

}
