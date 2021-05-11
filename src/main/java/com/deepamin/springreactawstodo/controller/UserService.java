package com.deepamin.springreactawstodo.controller;

import com.deepamin.springreactawstodo.model.User;
import com.deepamin.springreactawstodo.repository.ToDoRepository;
import com.deepamin.springreactawstodo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ToDoRepository toDoRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.loadUserByUsername(userName);
        if (user == null) throw  new UsernameNotFoundException("User with " + userName + " doesn't exist.");
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        User u = userRepository.loadUserByUsername(user.getUsername());
        if (u != null) throw  new UsernameNotFoundException("User with " + user.getUsername() + " already exists.");
        userRepository.save(user);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}
