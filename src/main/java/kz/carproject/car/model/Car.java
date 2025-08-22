package kz.carproject.car.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.NoSuchElementException;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private int id;
    private String modelName;
    private double engine;
    private double cost;
    private String color;
    private City city;

    @Getter
    public static ArrayList<Car> cars = new ArrayList<>();

    private static int idCar = 7;


    public static void addCar(Car car){
        car.setId(idCar);
        idCar++;
        cars.add(car);
    }

    public static Car getCarById(int id){
        return cars
                .stream()
                .filter(car-> car.id==id)
                .findFirst()
                .orElseThrow(()-> new NoSuchElementException("Car Not Found!"));
    }

    public static void updateCar(Car car){
        for(Car c: cars){
            if(c.id == car.getId()){
                c.setCost(car.getCost());
                c.setColor(car.getColor());
                c.setEngine(car.getEngine());
                c.setModelName(car.getModelName());
            }
        }
    }

    public static void deleteCar(int id){
        cars.removeIf(car->car.id==id);
    }


}
