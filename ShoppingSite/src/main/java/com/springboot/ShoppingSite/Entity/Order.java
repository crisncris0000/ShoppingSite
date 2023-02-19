package com.springboot.ShoppingSite.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private double price;
    private  String currency;
    private String method;
    private String intent;
    private String description;

    public void setPrice(List<Cart> cartList){

        for(Cart cart : cartList){
            price += cart.getItem().getPrice();
        }
    }


}
