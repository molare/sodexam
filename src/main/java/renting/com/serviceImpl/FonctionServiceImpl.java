package renting.com.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import renting.com.entities.Fonction;
import renting.com.repositories.FonctionRepository;
import renting.com.service.FonctionService;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by olivier on 02/10/2019.
 */
@Service("fonctionService")
public class FonctionServiceImpl implements FonctionService {
    @Autowired
    private FonctionRepository fonctionRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Fonction> getAll() {
        return fonctionRepository.findAll();
    }

    @Override
    public Fonction add(Fonction fonction) {
        return fonctionRepository.save(fonction);
    }

    @Override
    public Fonction update(Fonction fonction) {
        if(fonction.getId() ==0){
            return fonctionRepository.save(fonction);
        }
        return fonctionRepository.saveAndFlush(fonction);
    }

    @Override
    public Fonction findById(int id) {
        return fonctionRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        fonctionRepository.deleteById(id);
    }

    @Override
    public int countFonction(){
        String sql="SELECT DISTINCT COUNT(id) FROM fonction ";
        Query query = em.createNativeQuery(sql);
        try{
            return Integer.parseInt(query.getSingleResult()+"");
        }catch (NoResultException ex){
            return 0;
        }


    }
}
