package com.explore.playground.controller;

import com.explore.playground.model.Post;
import com.explore.playground.client.TypeicodeClient;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletionStage;

@RestController
@RequestMapping("api/v1/post")
public class PostController {

    private ModelMapper modelMapper;
    private TypeicodeClient typeicodeClient;

    public PostController(ModelMapper modelMapper, TypeicodeClient typeicodeClient) {
        this.modelMapper = modelMapper;
        this.typeicodeClient = typeicodeClient;
    }


    @GetMapping("/{id}")
    public CompletionStage<Post> getPostById(@PathVariable Long id) {
        return typeicodeClient.getPost(id);
    }

}
