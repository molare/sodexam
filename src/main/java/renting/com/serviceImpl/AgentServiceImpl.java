package renting.com.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import renting.com.entities.Agent;
import renting.com.repositories.AgentRepository;
import renting.com.service.AgentService;

import java.util.List;

/**
 * Created by olivier on 20/03/2020.
 */
@Service("agentService")
@Transactional
public class AgentServiceImpl implements AgentService {
    @Autowired
    private AgentRepository agentRepository;

    @Override
    public List<Agent> getAll() {
        return agentRepository.findAll();
    }

    @Override
    public Agent add(Agent agent) {
        return agentRepository.save(agent);
    }

    @Override
    public Agent update(Agent agent) {
        if(agent.getId()==0) {
            return agentRepository.save(agent);
        }
        return  agentRepository.saveAndFlush(agent);
    }

    @Override
    public Agent findById(int id) {
        return agentRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        agentRepository.deleteById(id);
    }
}
