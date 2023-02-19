package com.springboot.ShoppingSite.Service;

import com.springboot.ShoppingSite.Entity.Category;

import java.util.List;

public interface CategoryService {

    public Category findCategoryById(int id);

    public List<Category> findALl();

}
