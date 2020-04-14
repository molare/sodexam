package renting.com.service;

import renting.com.entities.Agent;

import java.util.List;

/**
 * Created by olivier on 20/03/2020.
 */
public interface AgentService {
    public List<Agent> getAll();
    public Agent add(Agent agent);
    public Agent update(Agent agent);
    public Agent findById(int id);
    public void delete(int id);
}
