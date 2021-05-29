package com.egg.library.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
}
