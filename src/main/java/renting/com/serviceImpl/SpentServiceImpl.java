package renting.com.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import renting.com.entities.Spent;
import renting.com.repositories.SpentRepository;
import renting.com.service.SpentService;

import java.util.List;

/**
 * Created by olivier on 02/10/2019.
 */
@Service("spentService")
public class SpentServiceImpl implements SpentService {
    @Autowired
    private SpentRepository spentRepository;

    @Override
    public List<Spent> getAll() {
        return spentRepository.findAll();
    }

    @Override
    public Spent add(Spent spent) {
        return spentRepository.save(spent);
    }

    @Override
    public Spent update(Spent spent) {
        if(spent.getId() ==0){
            return spentRepository.save(spent);
        }
        return spentRepository.saveAndFlush(spent);
    }

    @Override
    public Spent findById(int id) {
        return spentRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        spentRepository.deleteById(id);
    }
}
