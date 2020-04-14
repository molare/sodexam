package renting.com.service;

import renting.com.entities.Statut;

import java.util.List;

/**
 * Created by olivier on 02/10/2019.
 */
public interface StatutService {
    public List<Statut> getAll();
    public Statut add(Statut statut);
    public Statut update(Statut statut);
    public Statut findById(int id);
    public void delete(int id);
    public int countStatut();
}
