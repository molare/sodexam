package renting.com.repositories;

import renting.com.entities.StatutPay;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by olivier on 02/10/2019.
 */
public interface StatutPayRepository extends JpaRepository<StatutPay, Integer>{
    public StatutPay findById(int id);
}
