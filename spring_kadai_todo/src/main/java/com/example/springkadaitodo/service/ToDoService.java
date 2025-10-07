package com.example.springkadaitodo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springkadaitodo.entity.ToDo;
import com.example.springkadaitodo.repository.ToDoRepository;

@Service
public class ToDoService {
	ToDoRepository todoRepository;
	 public ToDoService(ToDoRepository todoRepository) {
	        this.todoRepository = todoRepository;
	    }

	 public List<ToDo> getAllTodos() {
		 return todoRepository.findAll();
	 }
	 
}
