package kz.carproject.car.db;

import kz.carproject.car.model.Car;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBConnector {

    private static Connection connection;
    private static String name = "postgres";
    private static String password = "postgres";
    private static String url = "jdbc:postgresql://localhost:5432/g138?CurrentSchema=cars";

    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url,name,password);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Car> getAllCars(){

            ArrayList<Car> cars = new ArrayList<>();

        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM cars.cars");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Car car = new Car();

                car.setId(resultSet.getInt("id"));
                car.setModelName(resultSet.getString("model_name"));
                car.setCost(resultSet.getDouble("cost"));
                car.setEngine(resultSet.getDouble("engine"));
                car.setColor(resultSet.getString("color"));

                cars.add(car);
            }

            resultSet.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return cars;
    }


}
