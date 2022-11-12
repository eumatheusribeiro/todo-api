package com.curso.todo.rest;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.curso.todo.model.Todo;
import com.curso.todo.repository.TodoRepository;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin("*")
public class TodoController {
	
	@Autowired
	private TodoRepository todoRepository;
	
	@GetMapping()
	public List<Todo> getAll() {
		return todoRepository.findAll();
	}
	
	@GetMapping("{id}")
	public Todo getById(@PathVariable Long id) {
		return todoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	 
	@PostMapping
	public Todo save(@RequestBody Todo todo) {
		 return todoRepository.save(todo);
	}
	
	@PatchMapping("{id}/done")
	public Todo markAsDone(@PathVariable Long id) {
		 return todoRepository.findById(id).map(todo -> {
			 todo.setDone(true);
			 todo.setDoneDate(LocalDateTime.now());
			 todoRepository.save(todo);
			 return todo;
		 }).orElse(null);
	}
	
	@DeleteMapping("{id}")
	public void deletePost(@PathVariable Long id) {
		 todoRepository.deleteById(id);
    }
}
