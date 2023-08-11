package com.explore.playground.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Cart {
    private int cardId;
    private HashMap<Item, Integer> items;
    private float cost;
}
