package com.explore.playground.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Item implements Cloneable {
    private Long id;
    private String name;
    private String description;
    private float price;
    private Map<String, String> details;
    private List<Review> reviews;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class Review {
        private int rating;
        private String reviewerName;
        private String comment;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Immutable");
    }
}
