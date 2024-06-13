package com.loginManagement.lms.service.impl;

import com.loginManagement.lms.dto.TodoDto;
import com.loginManagement.lms.entity.Todo;
import com.loginManagement.lms.exception.RecordNotFoundException;
import com.loginManagement.lms.mapper.TodoMapper;
import com.loginManagement.lms.repository.TodoRepo;
import com.loginManagement.lms.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {
    @Autowired
    TodoRepo todoRepo;

    /**
     * @return
     */
    @Override
    public List<TodoDto> getAllTodo() {
        return todoRepo.findAll().stream().map(p -> TodoMapper.convertToTodoDto(p)).collect(Collectors.toList());
    }

    /**
     * @param todoId
     * @return
     */
    @Override
    public TodoDto getTodoById(Long todoId) {
        return TodoMapper.convertToTodoDto(todoRepo.findById(todoId).orElseThrow(() -> new
                RecordNotFoundException("Todo Not Found " + todoId)));
    }

    /**
     * @param todoDto
     * @return
     */
    @Override
    public TodoDto saveTodo(TodoDto todoDto) {
        return TodoMapper.convertToTodoDto(todoRepo.save(TodoMapper.convertToTodoEntity(todoDto)));
    }

    /**
     * @param todoDto
     * @return
     */
    @Override
    public TodoDto updateTodo(Long todoId,TodoDto todoDto) {
        Todo todo = todoRepo.findById(todoId).orElseThrow(() ->
                new RecordNotFoundException("Todo Not present for Update" + todoId));
        todo.setTodoDescription(todoDto.getTodoDescription());
        todo.setTodoTitle(todoDto.getTodoTitle());
        todo.setComplete(todoDto.isComplete());
        return TodoMapper.convertToTodoDto(todoRepo.save(todo));
    }

    /**
     * @param todoId
     */
    @Override
    public void deleteTodo(Long todoId) {
        todoRepo.findById(todoId).orElseThrow(() -> new RecordNotFoundException("Todo Not Present in DB" + todoId));
        todoRepo.deleteById(todoId);
    }

    /**
     * @param todoId
     * @return
     */
    @Override
    public TodoDto completeTodo(Long todoId) {
        Todo todo =todoRepo.findById(todoId).orElseThrow(()->new RecordNotFoundException("Record not present "+todoId));
        todo.setComplete(Boolean.TRUE);
        return TodoMapper.convertToTodoDto(todoRepo.save(todo));
    }

    /**
     * @param todoId
     * @return
     */
    @Override
    public TodoDto inCompleteTodo(Long todoId) {
        Todo todo = todoRepo.findById(todoId).orElseThrow(
                ()->new RecordNotFoundException("Record not present "+todoId)
        );
        todo.setComplete(Boolean.FALSE);
        return TodoMapper.convertToTodoDto(todoRepo.save(todo));
    }
}
