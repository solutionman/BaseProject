package com.project.base.service;

import com.project.base.dao.RoleDao;
import com.project.base.dao.UserDao;
import com.project.base.model.Role;
import com.project.base.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save( User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        switch (user.getUsertype()) {
            case "admin":
                roles.add(roleDao.getOne(2L));
                break;
            case "user":
                roles.add(roleDao.getOne(1L));
                break;
            case "operator":
                roles.add(roleDao.getOne(4L));
                break;
            default: roles.add(roleDao.getOne(3L));
                break;
        }

        user.setRoles(roles);
        userDao.save(user);
    }

    public void update( User user ){
        userDao.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public List<User> findUsersByUsertype(String usertype) {
        return userDao.findUsersByUsertype(usertype);
    }

    @Override
    public User findById(long id) {
        return userDao.findById(id);
    }
}
