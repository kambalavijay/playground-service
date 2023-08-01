package com.explore.playground.controller;

import com.explore.playground.entity.User;
import com.explore.playground.controller.request.UserCreateRequest;
import com.explore.playground.controller.response.UserCreateResponse;
import com.explore.playground.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    @PostMapping
    public UserCreateResponse createUser(@RequestBody UserCreateRequest userCreateRequest) {
        User user = modelMapper.map(userCreateRequest, User.class);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserCreateResponse.class);
    }


    @PutMapping("{id}")
    public UserCreateResponse updateUser(@RequestBody UserCreateRequest userCreateRequest,
                                         @PathVariable Long id) {
        User user = modelMapper.map(userCreateRequest, User.class);
        user.setId(id);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserCreateResponse.class);
    }


}
