package com.explore.playground.service.impl;

import com.explore.playground.model.Cart;
import com.explore.playground.model.Item;
import com.explore.playground.service.CartService;
import com.explore.playground.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private OfferService offerService;

    //@Autowired
    //private CardDao cardDao;

    @Override
    public float calculateCartTotalPrice(Long cartID) {
        //Cart cart = cardDao.getCartbyID(cartID);
        Cart cart = new Cart(); // this would be fetched from databse
        float cartTotalPrice = 0f;
        for (Item item : cart.getItems().keySet()) {
            cartTotalPrice += (cart.getItems().get(item) * offerService.getOfferPrice(item));
        }
        return cartTotalPrice;
    }

    @Override
    public int addItem(Long cartID, Item item) {
        //Cart cart = cardDao.getCartbyID(cartID);
        Cart cart = new Cart();
        if(cart.getItems().containsKey(item)) {
            cart.getItems().put(item, cart.getItems().get(item)+1);
        }
        else{
            cart.getItems().put(item, 1);
        }
        return cart.getItems().size();
    }
}
