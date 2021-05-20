package com.deepamin.springreactawstodo.repository;

import com.deepamin.springreactawstodo.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void shouldLoadUserByUsername() {
        String testUser = "testUser";
        User user = new User(testUser, "testPass");
        underTest.save(user);

        User expectedUser = underTest.loadUserByUsername(testUser);
        assertEquals(user, expectedUser);
    }

    @Test
    void shouldNotLoadUserByUsername() {
        String testUser = "testUser";
        User expectedUser = underTest.loadUserByUsername(testUser);
        assertEquals(expectedUser, null);
    }
}