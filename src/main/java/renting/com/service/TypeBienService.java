package renting.com.service;

import renting.com.entities.TypeBien;
import renting.com.entities.TypePay;

import java.util.List;

/**
 * Created by olivier on 02/10/2019.
 */
public interface TypeBienService {
    public List<TypeBien> getAll();
    public TypeBien add(TypeBien typeBien);
    public TypeBien update(TypeBien typeBien);
    public TypeBien findById(int id);
    public void delete(int id);
}
