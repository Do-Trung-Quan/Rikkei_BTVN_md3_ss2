package com.ss2.taskmanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss2.taskmanagement.models.Task;
import com.ss2.taskmanagement.services.TaskService;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping 
    public ResponseEntity<List<Task>> list(){
        return ResponseEntity.ok(taskService.findAllTasks());
    }
}
