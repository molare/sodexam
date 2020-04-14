package renting.com.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import renting.com.entities.Role;
import renting.com.repositories.RoleRespository;
import renting.com.service.RoleService;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivier on 16/09/2019.
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRespository roleRespository;

    @Override
    public List<Role> getAll() {
        return roleRespository.findAll();
    }

    @Override
    public Role add(Role role) {
        return roleRespository.save(role);
    }

    @Override
    public Role update(Role role) {
        if(role.getId()==0){
            return roleRespository.save(role);
        }
        return roleRespository.saveAndFlush(role);
    }

    @Override
    public Role findById(int id) {
        return roleRespository.findById(id);
    }

    @Override
    public void delete(int id) {
        roleRespository.deleteById(id);
    }

    @Override
    public List<Role> export(int cpt, HttpServletRequest request) {
        List<Role> userList = new ArrayList<Role>();
        Role us = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //DecimalFormat dft = new DecimalFormat("#.00");

        for(int i=0; i<cpt; i++){

            us = roleRespository.findById(Integer.parseInt(request.getParameter("keyid"+i)));

           // us.setDateTransient(df.format(us.getDate()));
            //System.out.println("users "+cus);

            userList.add(us);
        }
        return userList;
    }
}
