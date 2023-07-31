package com.example.testvalidation;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Post {

    private Long id;
    private Long userId;
    private String title;
    private String body;
}
