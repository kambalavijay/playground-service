package com.explore.playground.service.impl;

import com.explore.playground.model.Item;
import com.explore.playground.service.DiscountService;
import com.explore.playground.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {

    @Autowired
    private DiscountService discountService;

    @Override
    public float getOfferPrice(Item item) {
        return item.getPrice() * discountService.getDiscountByItem(item.getId());
    }
}
