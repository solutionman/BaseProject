package com.project.base.service;

import com.project.base.model.User;

import java.util.List;

public interface UserService {

    void save(User user);

    User findByUsername(String username);

    List<User> findAll();

    User findById(long id);

    void update(User user);

    void delete(User user);
}
