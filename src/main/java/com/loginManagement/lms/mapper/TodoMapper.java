package com.loginManagement.lms.mapper;

import com.loginManagement.lms.dto.TodoDto;
import com.loginManagement.lms.entity.Todo;

public class TodoMapper {

    public static Todo convertToTodoEntity(TodoDto todoDto) {
        return new Todo(todoDto.getId(), todoDto.getTodoTitle(), todoDto.getTodoDescription(), todoDto.isComplete());
    }

    public static TodoDto convertToTodoDto(Todo todo) {
        return new TodoDto(todo.getId(), todo.getTodoTitle(), todo.getTodoDescription(), todo.isComplete());
    }
}
