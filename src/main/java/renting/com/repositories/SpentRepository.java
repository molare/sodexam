package renting.com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import renting.com.entities.Spent;

/**
 * Created by olivier on 02/10/2019.
 */
public interface SpentRepository extends JpaRepository<Spent, Integer>{
    public Spent findById(int id);
}
