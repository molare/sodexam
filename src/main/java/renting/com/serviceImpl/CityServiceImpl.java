package renting.com.serviceImpl;

import renting.com.entities.City;
import renting.com.entities.Commune;
import renting.com.repositories.CityRepository;
import renting.com.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by olivier on 02/10/2019.
 */
@Service("cityService")
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepository cityRepository;
    @Override
    public List<City> getAll() {
        return cityRepository.findAll();
    }

    @Override
    public City add(City city) {
        return cityRepository.save(city);
    }

    @Override
    public City update(City city) {
        if(city.getId() ==0){
            return cityRepository.save(city);
        }
        return cityRepository.saveAndFlush(city);
    }

    @Override
    public City findById(int id) {
        return cityRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        cityRepository.deleteById(id);
    }

    @Override
    public List<City> findByCommune(Commune commune) {
        return cityRepository.findByCommune(commune);
    }
}
