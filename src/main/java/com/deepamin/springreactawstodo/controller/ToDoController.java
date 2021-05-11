package com.deepamin.springreactawstodo.controller;

import com.deepamin.springreactawstodo.model.ToDo;
import com.deepamin.springreactawstodo.model.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/todos")
public class ToDoController {
    @Autowired
    private ToDoService toDoService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<ToDo> getToDos() {
        return toDoService.getToDos();
    }

//    @PostMapping
//    public void addToDo(@RequestBody ToDo toDo) {
//        toDoService.addToDo(toDo);
//    }

    @PutMapping
    public void addToDoToUser(@PathVariable Long userId, @RequestBody ToDo toDo) {
       User user = userService.findById(userId).orElseThrow(() -> new IllegalStateException("User with " + userId + " is not availble."));
       toDo.setUser(user);
       user.addToDo(toDo);
       toDoService.addToDo(toDo);
    }

    @DeleteMapping(path = "{toDoId}")
    public void deleteToDo(@PathVariable("toDoId") Long toDoId) {
        toDoService.deleteToDoById(toDoId);
    }

//    @Transactional
//    @PutMapping(path = "{toDoId}")
//    public void editToDo(@PathVariable("toDoId") Long toDoId) {
//        toDoService.
//    }
}
