package com.springboot.ShoppingSite.Service.Implementation;

import com.springboot.ShoppingSite.Entity.Cart;
import com.springboot.ShoppingSite.Entity.Item;
import com.springboot.ShoppingSite.Entity.User;
import com.springboot.ShoppingSite.Repository.CartRepository;
import com.springboot.ShoppingSite.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;
    @Override
    public void saveItem(Item item, User user) {
        Cart cart = new Cart();

        cart.setItem(item);
        cart.setUser(user);

        cartRepository.save(cart);
    }

    @Override
    public List<Cart> findItemsFromCart(String username) {

        List<Cart> listItems = cartRepository.showMyItems(username);

        return listItems;
    }

    @Override
    public List<Cart> findItemsInCart(int id) {

        List<Cart> cartList = cartRepository.findItemsInCart(id);

        return cartList;
    }

    @Override
    public void deleteAllCartItems(List<Cart> cartList) {
        cartRepository.deleteAll(cartList);
    }
}
