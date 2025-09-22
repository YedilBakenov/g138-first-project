package kz.carproject.car.dto;

import kz.carproject.car.model.CarPassport;
import kz.carproject.car.model.City;
import kz.carproject.car.model.Owner;
import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CarDto {
    private int id;
    private String name;
    private double engine;
    private double cena;
    private String color;
    private CarPassport carPassport;
    private List<City> cities;
    private Owner owner;
}
