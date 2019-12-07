package renting.com.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import renting.com.entities.Bien;
import renting.com.entities.BienAttachment;

import java.util.List;

/**
 * Created by olivier on 04/12/2019.
 */
public interface BienAttachmentRepository extends JpaRepository<BienAttachment, Integer>{
    public BienAttachment findById(int id);
    public List<BienAttachment> findByBien(Bien bien);
}
