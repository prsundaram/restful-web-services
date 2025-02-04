package com.psundara.rest.webservices.controller;

import com.psundara.rest.webservices.dao.PostDaoService;
import com.psundara.rest.webservices.dao.UserDaoService;
import com.psundara.rest.webservices.exception.UserNotFoundException;
import com.psundara.rest.webservices.model.Post;
import com.psundara.rest.webservices.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserResource {

    private final UserDaoService userDaoService;
    private final PostDaoService postDaoService;

    @Autowired
    public UserResource(UserDaoService userDaoService, PostDaoService postDaoService) {
        this.userDaoService = userDaoService;
        this.postDaoService = postDaoService;
    }


    @GetMapping("users")
    public List<User> retrieveAllUsers() {
        return userDaoService.finalAll();
    }

    @GetMapping("users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable Integer id) {
        User user = userDaoService.findOne(id);
        if (user == null)
            throw new UserNotFoundException("id " + id);

        EntityModel<User> entityModel =  EntityModel.of(user);
        WebMvcLinkBuilder webMvcLinkBuilder = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(webMvcLinkBuilder.withRel("all-users"));
        return entityModel;
    }

    @PostMapping("users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userDaoService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        User user = userDaoService.findOne(id);
        if (user == null)
            throw new UserNotFoundException("id " + id);
        userDaoService.deleteById(id);
    }

    @GetMapping("users/{id}/posts")
    public ResponseEntity<List<Post>> retrievePostsForUser(@PathVariable int id) {
        User user = userDaoService.findOne(id);
        if (user == null)
            throw new UserNotFoundException("id " + id);
        return ResponseEntity.ok(user.getPosts());
    }

    @PostMapping("users/{id}/posts")
    public ResponseEntity<Post> createPostForUser(@PathVariable Integer id, @Valid @RequestBody Post post) {
        User user = userDaoService.findOne(id);

        if (user == null)
            throw new UserNotFoundException("id " + id);

        post.setUser(user);

        Post savedPost = postDaoService.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}/")
                .buildAndExpand(savedPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
