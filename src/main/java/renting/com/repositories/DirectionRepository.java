package renting.com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import renting.com.entities.Direction;

/**
 * Created by olivier on 02/10/2019.
 */
public interface DirectionRepository extends JpaRepository<Direction, Integer>{
    public Direction findById(int id);
}
