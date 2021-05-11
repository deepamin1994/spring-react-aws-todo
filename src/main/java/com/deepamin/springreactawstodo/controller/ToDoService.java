package com.deepamin.springreactawstodo.controller;

import com.deepamin.springreactawstodo.model.ToDo;
import com.deepamin.springreactawstodo.repository.ToDoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ToDoService {
    @Autowired
    private final ToDoRepository toDoRepository;

    public List<ToDo> getToDos() {
        return toDoRepository.findAll();
    }

    public void addToDo(ToDo toDo) {
        toDoRepository.save(toDo);
    }

    public void deleteToDoById(Long id) {
        toDoRepository.deleteById(id);
    }

//    public void editToDo(Long id, ToDo toDo) {
//       toDoRepository.findById(id).orElseThrow(() -> new IllegalStateException("No To Do item associated for editing with id " + id));
//    }
}
