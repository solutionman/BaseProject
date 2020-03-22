package com.project.base.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_details")
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "username")
    String username;

    @Column(name = "first_name")
    String firstname;

    @Column(name = "last_name")
    String lastname;

    @Column(name = "gender")
    String gender;

    @Column(name = "password")
    String password;

    @Column(name = "status")
    String status;
}
