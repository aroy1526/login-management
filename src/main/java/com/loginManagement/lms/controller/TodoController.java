package com.loginManagement.lms.controller;

import com.loginManagement.lms.dto.TodoDto;
import com.loginManagement.lms.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {
    @Autowired
    public TodoService todoService;

    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodo(){
        return ResponseEntity.ok(todoService.getAllTodo());
    }
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodoById(@PathVariable("id") Long todoId){
        return ResponseEntity.ok(todoService.getTodoById(todoId));
    }
    @PostMapping
    public ResponseEntity<TodoDto> saveTodo(@RequestBody TodoDto todoDto){
        return new ResponseEntity<>(todoService.saveTodo(todoDto), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
        return ResponseEntity.ok("Delete Successfully");
    }
    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable Long id, @RequestBody TodoDto todoDto){
       return ResponseEntity.ok(todoService.updateTodo(id,todoDto));
    }

    @PatchMapping("{id}")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable Long id){
        return ResponseEntity.ok(todoService.completeTodo(id));
    }

    @PatchMapping
    public ResponseEntity<TodoDto> inComplete(@RequestParam Long id){
        return ResponseEntity.ok(todoService.inCompleteTodo(id));
    }
}
