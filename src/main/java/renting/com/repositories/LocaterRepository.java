package renting.com.repositories;

import renting.com.entities.Locater;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by olivier on 09/10/2019.
 */
public interface LocaterRepository extends JpaRepository<Locater,Integer> {
    public Locater findById(int id);
}
