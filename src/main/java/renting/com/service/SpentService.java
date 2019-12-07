package renting.com.service;

import renting.com.entities.Spent;

import java.util.List;

/**
 * Created by olivier on 02/10/2019.
 */
public interface SpentService {
    public List<Spent> getAll();
    public Spent add(Spent spent);
    public Spent update(Spent spent);
    public Spent findById(int id);
    public void delete(int id);
}
