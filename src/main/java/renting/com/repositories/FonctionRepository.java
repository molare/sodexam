package renting.com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import renting.com.entities.Fonction;

/**
 * Created by olivier on 02/10/2019.
 */
public interface FonctionRepository extends JpaRepository<Fonction, Integer>{
    public Fonction findById(int id);
}
