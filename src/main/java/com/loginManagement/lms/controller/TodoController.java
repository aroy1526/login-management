package com.loginManagement.lms.controller;

import com.loginManagement.lms.dto.TodoDto;
import com.loginManagement.lms.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/todo")
public class TodoController {
    @Autowired
    public TodoService todoService;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN','MANAGER')")
    public ResponseEntity<List<TodoDto>> getAllTodo(){
        return ResponseEntity.ok(todoService.getAllTodo());
    }
    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER','MANAGER')")
    public ResponseEntity<TodoDto> getTodoById(@PathVariable("id") Long todoId){
        return ResponseEntity.ok(todoService.getTodoById(todoId));
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TodoDto> saveTodo(@RequestBody TodoDto todoDto){
        return new ResponseEntity<>(todoService.saveTodo(todoDto), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    @PreAuthorize(("hasRole('ADMIN')"))
    public ResponseEntity<String> deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
        return ResponseEntity.ok("Delete Successfully");
    }
    @PutMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable Long id, @RequestBody TodoDto todoDto){
       return ResponseEntity.ok(todoService.updateTodo(id,todoDto));
    }

    @PatchMapping("{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable Long id){
        return ResponseEntity.ok(todoService.completeTodo(id));
    }

    @PatchMapping
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<TodoDto> inComplete(@RequestParam Long id){
        return ResponseEntity.ok(todoService.inCompleteTodo(id));
    }
}
