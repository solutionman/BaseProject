package com.project.base.service;

import com.project.base.model.Role;
import com.project.base.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = this.userService.findByUsername(userName);
        if(null == user){
            System.out.println("User not found " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }
        System.out.println("Found User: " + userName);
        Set<Role> roleSet = user.getRoles();
        List<String> roleNames = new ArrayList<>();
        for(Role role : roleSet){
            roleNames.add(role.getName());
        }
        List<GrantedAuthority> grantList = new ArrayList<>();
        if (null != roleNames){
            for (String rol : roleNames){
                GrantedAuthority authority = new SimpleGrantedAuthority(rol);
                grantList.add(authority);
            }
        }

        UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantList);

        return userDetails;
    }

}
