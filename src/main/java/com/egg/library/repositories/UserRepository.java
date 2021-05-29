package com.egg.library.repositories;

import com.egg.library.models.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<MyUser, Long> {

    MyUser findByUsername(String username);
}
