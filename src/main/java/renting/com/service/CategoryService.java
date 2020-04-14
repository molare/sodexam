package renting.com.service;

import renting.com.entities.Category;

import java.util.List;

/**
 * Created by olivier on 02/10/2019.
 */
public interface CategoryService {
    public List<Category> getAll();
    public Category add(Category category);
    public Category update(Category category);
    public Category findById(int id);
    public void delete(int id);
    public int countCategory();
}
