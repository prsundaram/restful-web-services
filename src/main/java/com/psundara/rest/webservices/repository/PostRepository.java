package com.psundara.rest.webservices.repository;

import com.psundara.rest.webservices.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
