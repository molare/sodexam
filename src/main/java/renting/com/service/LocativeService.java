package renting.com.service;

import renting.com.entities.Locative;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by olivier on 02/10/2019.
 */
public interface LocativeService {
    public List<Locative> getAll();
    public Locative add(Locative locative);
    public Locative update(Locative locative);
    public Locative findById(int id);
    public void delete(int id);
    /*public List<Locative> findByProperty(Property property);
    public List<Locative> findByCity(City city);*/
    public double garanty(int id);
   // public List<Locative> getLocativeNotInContrat();
    public int countLocative();
    public List<Locative> export(int cpt, HttpServletRequest request);
}
