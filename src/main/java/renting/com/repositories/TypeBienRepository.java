package renting.com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import renting.com.entities.TypeBien;

/**
 * Created by olivier on 02/10/2019.
 */
public interface TypeBienRepository extends JpaRepository<TypeBien, Integer>{
    public TypeBien findById(int id);
}
