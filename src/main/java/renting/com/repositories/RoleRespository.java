package renting.com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import renting.com.entities.Role;

/**
 * Created by olivier on 16/09/2019.
 */
public interface RoleRespository extends JpaRepository<Role,Integer> {
    public Role findById(int id);
}
