package com.ss2.taskmanagement.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping 
    public ResponseEntity<Task> create(
        @RequestBody Task newTask){
            Task t = taskService.addTask(newTask);
            if(t != null){
                return ResponseEntity.status(HttpStatus.CREATED).body(t);
            }else{
                return ResponseEntity.badRequest().build();
            }
        }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(
        @PathVariable int id,
        @RequestBody Task task
    ){
       Task res = taskService.updateTask(id, task);
       if(res == null) return ResponseEntity.notFound().build(); 
       return ResponseEntity.ok().body(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
        @PathVariable int id
    ){
        boolean res = taskService.deleteTask(id);
        if(res == false) return ResponseEntity.notFound().build();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
