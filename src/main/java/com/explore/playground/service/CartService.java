package com.explore.playground.service;

import com.explore.playground.model.Item;

public interface CartService {

    float calculateCartTotalPrice(Long cartID);

    int addItem(Long cartID, Item item);
}
