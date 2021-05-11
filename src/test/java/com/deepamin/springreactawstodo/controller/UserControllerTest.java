package com.deepamin.springreactawstodo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    PasswordEncoder encoder = new BCryptPasswordEncoder(10);

    @Test
    public void testPassword() {
        String password = "password";
        System.out.println("PASS = " + encoder.encode(password));
    }
}