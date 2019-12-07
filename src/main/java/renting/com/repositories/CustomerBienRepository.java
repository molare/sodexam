package renting.com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import renting.com.entities.CustomerBien;
import renting.com.entities.Locater;

import java.util.List;

/**
 * Created by olivier on 02/10/2019.
 */
public interface CustomerBienRepository extends JpaRepository<CustomerBien, Integer>{
    public CustomerBien findById(int id);
    public List<CustomerBien> findByLocater(Locater locater);
}
