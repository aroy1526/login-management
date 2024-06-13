package com.loginManagement.lms.repository;

import com.loginManagement.lms.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepo extends JpaRepository<Todo,Long> {
}
