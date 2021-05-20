package com.deepamin.springreactawstodo.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ToDoNotFoundException extends Exception {
    public ToDoNotFoundException(String message) {
        super(message);
    }
}
