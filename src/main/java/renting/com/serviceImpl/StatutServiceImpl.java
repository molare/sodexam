package renting.com.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import renting.com.entities.Statut;
import renting.com.repositories.StatutRepository;
import renting.com.service.StatutService;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by olivier on 02/10/2019.
 */
@Service("statutService")
public class StatutServiceImpl implements StatutService {
    @Autowired
    private StatutRepository statutRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Statut> getAll() {
        return statutRepository.findAll();
    }

    @Override
    public Statut add(Statut statut) {
        return statutRepository.save(statut);
    }

    @Override
    public Statut update(Statut statut) {
        if(statut.getId() ==0){
            return statutRepository.save(statut);
        }
        return statutRepository.saveAndFlush(statut);
    }

    @Override
    public Statut findById(int id) {
        return statutRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        statutRepository.deleteById(id);
    }

    @Override
    public int countStatut(){
        String sql="SELECT DISTINCT COUNT(id) FROM type_bien ";
        Query query = em.createNativeQuery(sql);
        try{
            return Integer.parseInt(query.getSingleResult()+"");
        }catch (NoResultException ex){
            return 0;
        }


    }
}
