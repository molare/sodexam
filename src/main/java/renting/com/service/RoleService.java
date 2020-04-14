package renting.com.service;



import renting.com.entities.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by olivier on 16/09/2019.
 */
public interface RoleService {
    public List<Role> getAll();
    public Role add(Role role);
    public Role update(Role role);
    public Role findById(int id);
    public void delete(int id);
    public List<Role>export(int cpt, HttpServletRequest request);
}
