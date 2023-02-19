package com.springboot.ShoppingSite.Service.Implementation;

import com.springboot.ShoppingSite.Entity.Item;
import com.springboot.ShoppingSite.Repository.ItemRepository;
import com.springboot.ShoppingSite.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Item> getItems() {
        List<Item> items = itemRepository.findAll();
        return items;
    }

    @Override
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Override
    public void deleteItem(Item item) {
        itemRepository.delete(item);
    }

    @Override
    public Item findItemById(int id) {
        Optional<Item> res = itemRepository.findById(id);

        if(res == null) throw new NotFoundException("Item not found");

        Item item = res.get();

        return item;
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public List<Item> findAllClothing() {
        return itemRepository.findAllClothing();
    }

    @Override
    public List<Item> findNumberOfClothingItems(int num) {

        List<Item> result = itemRepository.findAllClothing();

        List<Item> clothingList = new ArrayList<>();

        if(result.size() < 4){
            for(int i = 0; i < result.size(); i++){
                clothingList.add(result.get(i));
            }
        } else {
            for (int i = 0; i < num; i++) {
                clothingList.add(result.get(i));
            }
        }

        return clothingList;
    }

    @Override
    public List<Item> findAllCosmetics() {
        return itemRepository.findAllCosmetics();
    }

    @Override
    public List<Item> findNumberOfCosmeticItems(int num) {

        List<Item> result = itemRepository.findAllCosmetics();

        List<Item> cosmeticList = new ArrayList<>();

        if(result.size() < 4){
            for(int i = 0; i < result.size(); i++){
                cosmeticList.add(result.get(i));
            }
        } else {
            for (int i = 0; i < num; i++) {
                cosmeticList.add(result.get(i));
            }
        }


        return cosmeticList;
    }

    @Override
    public List<Item> findAllCelebration() {
        return itemRepository.findAllCelebration();
    }

    @Override
    public List<Item> findNumberOfCelebrationItems(int num) {

        List<Item> result = itemRepository.findAllCelebration();

        List<Item> celebrationList = new ArrayList<>();

        if(result.size() < 4){
            for(int i = 0; i < result.size(); i++){
                celebrationList.add(result.get(i));
            }
        } else {
            for (int i = 0; i < num; i++) {
                celebrationList.add(result.get(i));
            }
        }

        return celebrationList;
    }

    @Override
    public List<Item> findAllOthers() {
        return findAllOthers();
    }

    @Override
    public List<Item> findNumberOfOtherItems(int num) {

        List<Item> result = itemRepository.findAllOthers();

        List<Item> otherList = new ArrayList<>();

        if(result.size() < 4){
            for(int i = 0; i < result.size(); i++){
                otherList.add(result.get(i));
            }
        } else {
            for (int i = 0; i < num; i++) {
                otherList.add(result.get(i));
            }
        }

        return otherList;
    }
}
