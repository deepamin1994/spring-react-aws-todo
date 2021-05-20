package com.deepamin.springreactawstodo.controller;

import com.deepamin.springreactawstodo.exception.ToDoNotFoundException;
import com.deepamin.springreactawstodo.model.ToDo;
import com.deepamin.springreactawstodo.repository.ToDoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ToDoServiceTest {

    private ToDoService underTest;
    @Mock
    private ToDoRepository toDoRepository;

    @BeforeEach
    void setUp() {
        underTest = new ToDoService(toDoRepository);
    }

    @Test
    void shouldGetToDos() {
        underTest.getToDos();
        verify(toDoRepository).findAll();
    }

    @Test
    void shouldAddToDo() {
        ToDo toDo = new ToDo();
        underTest.addToDo(toDo);
        ArgumentCaptor<ToDo> captor = ArgumentCaptor.forClass(ToDo.class);
        verify(toDoRepository).save(captor.capture());

        ToDo capturedToDo = captor.getValue();
        assertEquals(toDo, capturedToDo);
    }

    @Test
    void shouldDeleteToDoById() throws ToDoNotFoundException {
        Long id = 1L;
        when(toDoRepository.existsById(id)).thenReturn(true);
        underTest.deleteToDoById(id);
        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        verify(toDoRepository).deleteById(captor.capture());

        Long capturedId = captor.getValue();
        assertEquals(id, capturedId);
    }

    @Test
    void shouldThrowWhenDeleteToDoNotExists() throws ToDoNotFoundException {
        Long id = 1L;
        assertThrows(ToDoNotFoundException.class, () -> underTest.deleteToDoById(id));
    }
}