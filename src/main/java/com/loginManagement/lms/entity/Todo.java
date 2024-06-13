package com.loginManagement.lms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "todo_list")
public class Todo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(name = "todo_title")
    private String todoTitle;
    @Column(name = "todo_desc")
    private String todoDescription;
    private boolean complete;
}
