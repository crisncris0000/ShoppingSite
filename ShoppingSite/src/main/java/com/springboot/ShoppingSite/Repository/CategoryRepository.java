package com.springboot.ShoppingSite.Repository;

import com.springboot.ShoppingSite.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    public Category findById(int id);


}
