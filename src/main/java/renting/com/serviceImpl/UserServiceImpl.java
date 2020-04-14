package renting.com.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import renting.com.entities.User;
import renting.com.repositories.UserRespository;
import renting.com.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivier on 16/09/2019.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRespository userRespository;

    @Override
    public List<User> getAll() {
        return userRespository.findAll();
    }

    @Override
    public User add(User user) {
        return userRespository.save(user);
    }

    @Override
    public User update(User user) {
        if(user.getId()==0){
            return  userRespository.save(user);
        }
        return userRespository.saveAndFlush(user);
    }

    @Override
    public User findById(int id) {
        return userRespository.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRespository.findByUsername(username);
    }

    @Override
    public void delete(int id) {
        userRespository.deleteById(id);
    }

    @Override
    public List<User> export(int cpt, HttpServletRequest request) {
        List<User> userList = new ArrayList<User>();
        User us = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //DecimalFormat dft = new DecimalFormat("#.00");

        for(int i=0; i<cpt; i++){

            us = userRespository.findById(Integer.parseInt(request.getParameter("keyid"+i)));

            us.setDateTransient(df.format(us.getDate()));
            us.setDateTransient(df.format(us.getDate()));
            us.setRoleTransient(us.getRole().getName());
            if(us.isActive()){
                us.setActiveTransient("Oui");
            }else{
                us.setActiveTransient("Non");

            }
            //System.out.println("users "+cus);

            userList.add(us);
        }
        return userList;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) {
       /* User user = userRespository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return (UserDetails) new MyUserPrincipal(user);*/
        return null;
    }




  /*  @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        *//*Here we are using dummy data, you need to load user data from
     database or other third party application*//*
        User user = findByUsername(s);

        org.springframework.security.core.userdetails.User.UserBuilder builder = null;
        if (user != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(s);
            builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
            builder.roles(user.getRole().getName());
        } else {
            throw new UsernameNotFoundException("User not found.");
        }

        return builder.build();
    }*/

}
