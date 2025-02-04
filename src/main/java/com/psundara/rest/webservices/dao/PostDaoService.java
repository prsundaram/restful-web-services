package com.psundara.rest.webservices.dao;

import com.psundara.rest.webservices.model.Post;
import com.psundara.rest.webservices.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostDaoService {

    private final PostRepository postRepository;

    @Autowired
    public PostDaoService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }
}
