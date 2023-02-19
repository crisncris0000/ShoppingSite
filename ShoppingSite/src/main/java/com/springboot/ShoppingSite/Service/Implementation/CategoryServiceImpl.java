package com.springboot.ShoppingSite.Service.Implementation;

import com.springboot.ShoppingSite.Entity.Category;
import com.springboot.ShoppingSite.Repository.CategoryRepository;
import com.springboot.ShoppingSite.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;


    @Override
    public Category findCategoryById(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> findALl() {
        return categoryRepository.findAll();
    }
}
