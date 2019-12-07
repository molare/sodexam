package renting.com.repositories;

import renting.com.entities.Commune;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by olivier on 02/10/2019.
 */
public interface CommuneRepository  extends JpaRepository<Commune, Integer>{
    public Commune findById(int id);
}
