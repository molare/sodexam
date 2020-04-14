package renting.com.service;

import renting.com.entities.Fonction;

import java.util.List;

/**
 * Created by olivier on 02/10/2019.
 */
public interface FonctionService {
    public List<Fonction> getAll();
    public Fonction add(Fonction fonction);
    public Fonction update(Fonction fonction);
    public Fonction findById(int id);
    public void delete(int id);
    public int countFonction();
}
