package renting.com.service;

import renting.com.entities.Civility;
import renting.com.entities.CustomerBien;
import renting.com.entities.Locater;

import java.util.List;

/**
 * Created by olivier on 02/10/2019.
 */
public interface CustomerBienService {
    public List<CustomerBien> getAll();
    public CustomerBien add(CustomerBien customerBien);
    public CustomerBien update(CustomerBien customerBien);
    public CustomerBien findById(int id);
    public void delete(int id);
    public List<CustomerBien> findByLocater(Locater locater);

}
