package renting.com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import renting.com.entities.Category;

/**
 * Created by olivier on 02/10/2019.
 */
public interface CategoryRepository extends JpaRepository<Category, Integer>{
    public Category findById(int id);
}
