package com.curso.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.todo.model.Todo; 

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
