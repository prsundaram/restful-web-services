package com.psundara.rest.webservices.repository;

import com.psundara.rest.webservices.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
