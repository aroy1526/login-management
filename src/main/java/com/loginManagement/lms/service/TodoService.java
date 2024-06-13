package com.loginManagement.lms.service;

import com.loginManagement.lms.dto.TodoDto;

import java.util.List;

public interface TodoService {
    List<TodoDto> getAllTodo();
    TodoDto getTodoById(Long todoId);
    TodoDto saveTodo(TodoDto todoDto);
    TodoDto updateTodo(Long todoId,TodoDto todoDto);
    void deleteTodo(Long todoId);
    TodoDto completeTodo(Long todoId);
    TodoDto inCompleteTodo(Long todoId);
}
