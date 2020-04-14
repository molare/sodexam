package renting.com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import renting.com.entities.Agent;

/**
 * Created by olivier on 09/10/2019.
 */
public interface AgentRepository extends JpaRepository<Agent,Integer> {
    public Agent findById(int id);
}
