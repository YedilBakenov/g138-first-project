package kz.carproject.car.repository;

import jakarta.transaction.Transactional;
import kz.carproject.car.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface CarRepository extends JpaRepository<Car, Integer> {

    Car findCarByColor(String name);

    @Query("SELECT c FROM Car c " +
            "WHERE c.modelName ilike concat('%', :word, '%') " +
            "OR c.color ilike concat('%', :word, '%')")
    List<Car> searchCarsByWord(String word);
}
