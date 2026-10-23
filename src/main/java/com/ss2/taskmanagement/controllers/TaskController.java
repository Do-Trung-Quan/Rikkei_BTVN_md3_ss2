package com.ss2.taskmanagement.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<List<Task>> list(
        @RequestParam(value = "search", required = false) String search
    ){
        List<Task> allTasks = taskService.findAllTasks();
        if (search == null || search.trim().isEmpty()) {
            return ResponseEntity.ok(allTasks);
        }

        List<Task> res = new ArrayList<>();
        String keyword = search.trim().toLowerCase();
        for (Task t : allTasks) {
            if (t.getTitle() != null && t.getTitle().toLowerCase().contains(keyword)) {
                res.add(t);
            }
        }
        return ResponseEntity.ok(res);
    }
}
