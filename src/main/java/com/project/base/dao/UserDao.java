package com.project.base.dao;

import com.project.base.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findAll();
    User findById(long id);
}
