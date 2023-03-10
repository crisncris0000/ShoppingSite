package com.springboot.ShoppingSite.controller;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.springboot.ShoppingSite.Entity.Cart;
import com.springboot.ShoppingSite.Entity.Order;
import com.springboot.ShoppingSite.Service.CartService;
import com.springboot.ShoppingSite.Service.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PayPalController {

    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";

    @Autowired
    PaypalService paypalService;

    @Autowired
    CartService cartService;

    @PostMapping("/pay")
    public String payment(@ModelAttribute("order") Order order){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        List<Cart> cartList = cartService.findItemsFromCart(authentication.getName());

        try {
            order.setPrice(cartList);
            order.setCurrency("USD");
            order.setIntent("sale");
            order.setMethod("paypal");

            Payment payment = paypalService.createPayment(order.getPrice(), order.getCurrency(),
                    order.getMethod(), order.getIntent(), order.getDescription(),
                    "http://localhost:8080/" + CANCEL_URL, "http://localhost:8080/" + SUCCESS_URL);

            for(Links link : payment.getLinks()){
                if(link.getRel().equals("approval_url")){
                    return "redirect:"+link.getHref();
                }
            }

        } catch (PayPalRESTException e){
            e.printStackTrace();
        }

        return "redirect:/";
    }

    @GetMapping(value = CANCEL_URL)
    public String cancelPay(){
        return "cancel";
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "redirect:/home";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/home";
    }

}
