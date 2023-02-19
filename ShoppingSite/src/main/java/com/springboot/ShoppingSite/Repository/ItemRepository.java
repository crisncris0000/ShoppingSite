package com.springboot.ShoppingSite.Repository;

import com.springboot.ShoppingSite.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Query("FROM Item WHERE category_id = 1")
    List<Item> findAllClothing();

    @Query("FROM Item WHERE category_id = 2")
    List<Item> findAllCosmetics();

    @Query("FROM Item WHERE category_id = 3")
    List<Item> findAllCelebration();

    @Query("FROM Item WHERE category_id = 4")
    List<Item> findAllOthers();

}
