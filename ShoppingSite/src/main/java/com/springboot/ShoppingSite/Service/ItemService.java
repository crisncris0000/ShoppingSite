package com.springboot.ShoppingSite.Service;

import com.springboot.ShoppingSite.Entity.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ItemService {

    public List<Item> getItems();

    public void saveItem(Item item);

    public void deleteItem(Item item);

    public Item findItemById(int id);

    public List<Item> findAll();

    public List<Item> findAllClothing();

    public List<Item> findNumberOfClothingItems(int num);

    public List<Item> findAllCosmetics();

    public List<Item> findNumberOfCosmeticItems(int num);

    public List<Item> findAllCelebration();

    public List<Item> findNumberOfCelebrationItems(int num);

    public List<Item> findAllOthers();

    public List<Item> findNumberOfOtherItems(int num);



}
