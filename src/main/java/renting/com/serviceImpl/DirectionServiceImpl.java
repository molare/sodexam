package renting.com.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import renting.com.entities.Direction;
import renting.com.repositories.DirectionRepository;
import renting.com.service.DirectionService;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by olivier on 02/10/2019.
 */
@Service("directionService")
public class DirectionServiceImpl implements DirectionService {
    @Autowired
    private DirectionRepository directionRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Direction> getAll() {
        return directionRepository.findAll();
    }

    @Override
    public Direction add(Direction direction) {
        return directionRepository.save(direction);
    }

    @Override
    public Direction update(Direction direction) {
        if(direction.getId() ==0){
            return directionRepository.save(direction);
        }
        return directionRepository.saveAndFlush(direction);
    }

    @Override
    public Direction findById(int id) {
        return directionRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        directionRepository.deleteById(id);
    }

    @Override
    public int countDirection(){
        String sql="SELECT DISTINCT COUNT(id) FROM type_bien ";
        Query query = em.createNativeQuery(sql);
        try{
            return Integer.parseInt(query.getSingleResult()+"");
        }catch (NoResultException ex){
            return 0;
        }


    }
}
