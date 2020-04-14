package renting.com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import renting.com.entities.User;

/**
 * Created by olivier on 11/04/2020.
 */
public interface UserRespository extends JpaRepository<User, Integer>{
    public User findById(int id);
    public User findByUsername(String username);
}
