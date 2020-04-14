package renting.com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import renting.com.entities.Statut;

/**
 * Created by olivier on 02/10/2019.
 */
public interface StatutRepository extends JpaRepository<Statut, Integer>{
    public Statut findById(int id);
}
