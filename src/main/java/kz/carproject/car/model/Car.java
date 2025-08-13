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

    @Getter
    public static ArrayList<Car> cars = new ArrayList<>();

    private static int idCar = 7;

    static {
        cars.add(new Car(1, "BMW X3", 2.0, 60000, "red"));
        cars.add(new Car(2, "BMW X4", 2.0, 60000, "blue"));
        cars.add(new Car(3, "BMW X5", 2.0, 60000, "red"));
        cars.add(new Car(4, "MERCEDES G-35", 5.0, 120000, "black"));
        cars.add(new Car(5, "MERCEDES S-500", 5.0, 140000, "yellow"));
        cars.add(new Car(6, "MERCEDES S-500", 5.0, 140000, "yellow"));
    }

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
