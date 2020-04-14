package renting.com.service;

import renting.com.entities.Direction;

import java.util.List;

/**
 * Created by olivier on 02/10/2019.
 */
public interface DirectionService {
    public List<Direction> getAll();
    public Direction add(Direction direction);
    public Direction update(Direction direction);
    public Direction findById(int id);
    public void delete(int id);
    public int countDirection();
}
