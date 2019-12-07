package renting.com.repositories;


import renting.com.entities.Bien;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by olivier on 02/10/2019.
 */
public interface BienRepository extends JpaRepository<Bien, Integer> {
    public Bien findById(int id);

}
