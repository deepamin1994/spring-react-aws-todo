package com.deepamin.springreactawstodo.controller;

import com.deepamin.springreactawstodo.exception.UserException;
import com.deepamin.springreactawstodo.model.User;
import com.deepamin.springreactawstodo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    private UserService underTest;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        underTest = new UserService(userRepository);
    }

    @Test
    void shouldLoadUserByUsername() {
        String userName = "testUser";
        User user = new User(userName, "pass");
        when(userRepository.loadUserByUsername(userName)).thenReturn(user);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        underTest.loadUserByUsername(userName);

        verify(userRepository).loadUserByUsername(captor.capture());

        String capturedUsername = captor.getValue();
        assertEquals(userName, capturedUsername);
    }

    @Test
    void shouldThrowWhenUsernameNotExists() {
        String userName = "testUser";
        User user = new User(userName, "pass");

        assertThrows(UsernameNotFoundException.class, () -> underTest.loadUserByUsername(userName));
    }

    @Test
    void shouldGetAllUsers() {
        underTest.getAllUsers();
        verify(userRepository).findAll();
    }

    @Test
    void shouldAddUser() throws UserException {
        String userName = "testUser";
        User user = new User(userName, "pass");
        underTest.addUser(user);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());

        User capturedUser = captor.getValue();
        assertEquals(user, capturedUser);
    }

    @Test
    void shouldThrowWhenUserAlreadyExists() {
        String userName = "testUser";
        User user = new User(userName, "pass");
        when(userRepository.loadUserByUsername(userName)).thenReturn(user);

        assertThrows(UserException.class, () -> underTest.addUser(user));
        verify(userRepository, never()).save(any());
    }

    @Test
    void shouldFindById() {
        Long id= 1L;
        underTest.findById(id);
        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        verify(userRepository).findById(captor.capture());

        Long capturedId = captor.getValue();
        assertEquals(id, capturedId);
    }
}