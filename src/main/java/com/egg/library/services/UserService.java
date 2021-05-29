package com.egg.library.services;

import com.egg.library.models.MyUser;
import com.egg.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional
    public void createUser(String username, String password) {
        MyUser myUser = new MyUser();
        myUser.setUsername(username);
        myUser.setPassword(encoder.encode(password));
        userRepository.save(myUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser = userRepository.findByUsername(username);
        if (myUser == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new User(myUser.getUsername(), myUser.getPassword(), Collections.emptyList());
    }
}
