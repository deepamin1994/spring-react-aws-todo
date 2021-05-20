package com.deepamin.springreactawstodo.controller;

import com.deepamin.springreactawstodo.exception.ToDoNotFoundException;
import com.deepamin.springreactawstodo.model.ToDo;
import com.deepamin.springreactawstodo.repository.ToDoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public void deleteToDoById(Long id) throws ToDoNotFoundException {
        if (!toDoRepository.existsById(id)) {
            throw new ToDoNotFoundException("ToDo with id " + id + " doesn't exist.");
        }
        toDoRepository.deleteById(id);
    }

//    @Transactional
//    public void editToDo(ToDo toDo) {
//       toDoRepository.findById(toDo.getId()).orElseThrow(() -> new IllegalStateException("No To Do item associated for editing with id " + toDo.getId()));
//       toDoRepository.save(toDo);
//    }
}
