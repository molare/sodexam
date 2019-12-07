package renting.com.repositories;

import renting.com.entities.City;
import renting.com.entities.Commune;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by olivier on 02/10/2019.
 */
public interface CityRepository extends JpaRepository<City,Integer> {
    public City findById(int id);
    public List<City> findByCommune(Commune commune);
}
