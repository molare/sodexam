package renting.com.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import renting.com.entities.Civility;
import renting.com.entities.CustomerBien;
import renting.com.entities.Locater;
import renting.com.repositories.CivilityRepository;
import renting.com.repositories.CustomerBienRepository;
import renting.com.service.CivilityService;
import renting.com.service.CustomerBienService;

import java.util.List;

/**
 * Created by olivier on 02/10/2019.
 */
@Service("customerBienService")
public class CustomerBienServiceImpl implements CustomerBienService {
    @Autowired
    private CustomerBienRepository customerBienRepository;

    @Override
    public List<CustomerBien> getAll() {
        return customerBienRepository.findAll();
    }

    @Override
    public CustomerBien add(CustomerBien customerBien) {
        return customerBienRepository.save(customerBien);
    }

    @Override
    public CustomerBien update(CustomerBien customerBien) {
        if(customerBien.getId() ==0){
            return customerBienRepository.save(customerBien);
        }
        return customerBienRepository.saveAndFlush(customerBien);
    }

    @Override
    public CustomerBien findById(int id) {
        return customerBienRepository.findById(id);
    }

    @Override
    public void delete(int id){
        customerBienRepository.deleteById(id);
    }

    @Override
    public List<CustomerBien> findByLocater(Locater locater) {
        return customerBienRepository.findByLocater(locater);
    }
}
