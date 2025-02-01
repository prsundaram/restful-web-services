package com.psundara.rest.webservices.restful_web_services.controller;

import com.psundara.rest.webservices.restful_web_services.dao.UserDaoService;
import com.psundara.rest.webservices.restful_web_services.exception.UserNotFoundException;
import com.psundara.rest.webservices.restful_web_services.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    private final UserDaoService service;
    private URI location;

    @Autowired
    public UserResource(UserDaoService service) {
        this.service = service;
    }


    @GetMapping("users")
    public List<User> retrieveAllUsers() {
        return service.finalAll();
    }

    @GetMapping("users/{id}")
    public User retrieveUser(@PathVariable Integer id) {
        User user = service.findOne(id);
        if (user == null)
            throw new UserNotFoundException("id " + id);
        return user;
    }

    @PostMapping("users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        User user = service.findOne(id);
        if (user == null)
            throw new UserNotFoundException("id " + id);
        service.deleteById(id);
    }
}
