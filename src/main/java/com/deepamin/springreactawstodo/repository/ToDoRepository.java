package com.deepamin.springreactawstodo.repository;

import com.deepamin.springreactawstodo.model.ToDo;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    //TODO public List<ToDo> findByCompleted(boolean completed);

    //TODO query for title?
//    List<ToDo> findByTextContaining(String text);
}
