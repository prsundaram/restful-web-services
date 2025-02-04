package com.psundara.rest.webservices.dao;

import com.psundara.rest.webservices.model.User;
import com.psundara.rest.webservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDaoService {

    private final UserRepository userRepository;

    @Autowired
    public UserDaoService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> finalAll() {
        return userRepository.findAll();
    }

    public User findOne(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }
}
