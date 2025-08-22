package kz.carproject.car.db;

import kz.carproject.car.model.Car;
import kz.carproject.car.model.City;

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
            connection = DriverManager.getConnection(url, name, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Car> getAllCars() {

        ArrayList<Car> cars = new ArrayList<>();

        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM cars.cars car " +
                    "INNER JOIN cars.cities city " +
                    "ON city.id = car.city_id order by car.id ASC");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Car car = new Car();

                car.setId(resultSet.getInt("id"));
                car.setModelName(resultSet.getString("model_name"));
                car.setCost(resultSet.getDouble("cost"));
                car.setEngine(resultSet.getDouble("engine"));
                car.setColor(resultSet.getString("color"));

                City city = new City();
                city.setId(resultSet.getInt("city_id"));
                city.setCode(resultSet.getString("code"));
                city.setName(resultSet.getString("name"));
                city.setValue(resultSet.getInt("value"));

                car.setCity(city);

                cars.add(car);
            }

            resultSet.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cars;
    }

    public static Car getCarById(int id) {

        Car car = new Car();

        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM cars.cars car " +
                    "INNER JOIN cars.cities city " +
                    "ON city.id = car.city_id WHERE car.id=?");

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                car.setId(resultSet.getInt("id"));
                car.setColor(resultSet.getString("color"));
                car.setCost(resultSet.getDouble("cost"));
                car.setEngine(resultSet.getDouble("engine"));
                car.setModelName(resultSet.getString("model_name"));

                City city = new City();
                city.setId(resultSet.getInt("city_id"));
                city.setCode(resultSet.getString("code"));
                city.setName(resultSet.getString("name"));
                city.setValue(resultSet.getInt("value"));

                car.setCity(city);

            }

            resultSet.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return car;
    }

    public static ArrayList<City>getAllCities(){
        ArrayList<City> cities = new ArrayList<>();

        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM cars.cities");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                City city = new City();
                city.setId(resultSet.getInt("id"));
                city.setName(resultSet.getString("name"));
                city.setCode(resultSet.getString("code"));
                city.setValue(resultSet.getInt("value"));

                cities.add(city);
            }

            resultSet.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return cities;

    }

    public static void addCar(Car car) {

        try {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO cars.cars(model_name, engine, cost, color, city_id) " +
                    "VALUES(?,?,?,?,?)");
            statement.setString(1, car.getModelName());
            statement.setDouble(2, car.getEngine());
            statement.setDouble(3, car.getCost());
            statement.setString(4, car.getColor());
            statement.setInt(5, car.getCity().getId());

            statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateCar(Car car){

        try{

            PreparedStatement statement = connection.prepareStatement("UPDATE cars.cars SET model_name=?, engine=?, " +
                    "cost=?, color=?, city_id=? WHERE id=?");

            statement.setString(1, car.getModelName());
            statement.setDouble(2, car.getEngine());
            statement.setDouble(3, car.getCost());
            statement.setString(4, car.getColor());
            statement.setInt(5, car.getCity().getId());
            statement.setInt(6, car.getId());

            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void deleteCar(int id) {

        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM cars.cars WHERE id=?");
            statement.setInt(1, id);

            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
