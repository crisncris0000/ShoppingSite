package com.springboot.ShoppingSite.Service;

import com.springboot.ShoppingSite.Entity.Cart;
import com.springboot.ShoppingSite.Entity.Item;
import com.springboot.ShoppingSite.Entity.User;

import java.util.List;

public interface CartService {

    public void saveItem(Item item, User user);

    public List<Cart> findItemsFromCart(String username);

    public List<Cart> findItemsInCart(int id);

    public void deleteAllCartItems(List<Cart> cartList);

}
