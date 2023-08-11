package com.explore.playground.service.impl;

import com.explore.playground.service.DiscountService;
import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl implements DiscountService {

    //@Autowired
    //private DiscountDao discountDao;

    @Override
    public float getDiscountByItem(Long itemID) {
        // Note: if there is no offer associated with an item the default discount is 1;
        return 10.0f;
    }
}
