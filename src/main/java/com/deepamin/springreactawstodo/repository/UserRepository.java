package com.deepamin.springreactawstodo.repository;

import com.deepamin.springreactawstodo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username = ?1")
    User loadUserByUsername(String userName);
}
