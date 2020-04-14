package renting.com.service;

import renting.com.entities.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by olivier on 16/09/2019.
 */
public interface UserService {
    public List<User> getAll();
    public User add(User user);
    public User update(User user);
    public User findById(int id);
    public User findByUsername(String username);
    public void delete(int id);
    public List<User>export(int cpt, HttpServletRequest request);
}
