package com.springboot.ShoppingSite.repository;

import com.springboot.ShoppingSite.Entity.Category;
import com.springboot.ShoppingSite.Entity.Item;
import com.springboot.ShoppingSite.Repository.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void ItemRepository_SaveItem_ReturnSavedItem(){
        Item item = Item.builder()
                .id(1)
                .text("This is a test")
                .image("Test image")
                .price(55)
                .quantity(2)
                .category(new Category(1, "Clothing"))
                .build();

        Item savedItem = itemRepository.save(item);

        Assertions.assertThat(savedItem).isNotNull();
        Assertions.assertThat(savedItem.getId()).isGreaterThan(0);
    }

    @Test
    public void ItemRepository_FindItemByCategory_ReturnMoreThenOneItem(){
        Item clothingItem = Item.builder()
                .id(1)
                .text("This is a test")
                .image("Test image")
                .price(55)
                .quantity(2)
                .category(new Category(1, "Clothing"))
                .build();

        Item clothingItem2 = Item.builder()
                .id(2)
                .text("This is a test")
                .image("Test image")
                .price(55)
                .quantity(2)
                .category(new Category(1, "Clothing"))
                .build();

        Item findOtherItem = Item.builder()
                .id(2)
                .text("This is a test")
                .image("Test image")
                .price(55)
                .quantity(2)
                .category(new Category(1, "Other"))
                .build();

        itemRepository.save(clothingItem);
        itemRepository.save(clothingItem2);

        List<Item> clothingItems = itemRepository.findAllClothing();

        Assertions.assertThat(clothingItems.size()).isEqualTo(2);

        for(Item item : clothingItems){
            Assertions.assertThat(clothingItem.getCategory().getCategoryName()).isEqualTo("Clothing");
        }
    }

    @Test
    public void ItemRepository_FindItemById_ReturnItemById(){
        Item item = Item.builder()
                .id(1)
                .text("This is a test")
                .image("Test image")
                .price(55)
                .quantity(2)
                .category(new Category(1, "Clothing"))
                .build();

        itemRepository.save(item);

        Item findItem = itemRepository.findById(1).get();

        Assertions.assertThat(findItem).isNotNull();
        Assertions.assertThat(findItem.getId()).isEqualTo(1);
    }

    @Test
    public void ItemRepository_DeleteItem_DeleteAnItem(){
        Item item = Item.builder()
                .id(1)
                .text("This is a test")
                .image("Test image")
                .price(55)
                .quantity(2)
                .category(new Category(1, "Clothing"))
                .build();

        itemRepository.delete(item);

        boolean itemExist = itemRepository.findById(1).isPresent();

        Assertions.assertThat(itemExist).isEqualTo(false);
    }



}
