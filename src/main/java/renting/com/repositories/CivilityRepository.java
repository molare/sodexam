package renting.com.repositories;

import renting.com.entities.Civility;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by olivier on 02/10/2019.
 */
public interface CivilityRepository extends JpaRepository<Civility, Integer>{
    public Civility findById(int id);
}
