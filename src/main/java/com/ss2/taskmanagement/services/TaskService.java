package com.ss2.taskmanagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ss2.taskmanagement.models.Task;
import com.ss2.taskmanagement.repositories.TaskRepository;

@Service
public class TaskService {
    private TaskRepository taskRepository;
    
    @Autowired 
    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public List<Task> findAllTasks(){
        return taskRepository.findAll();
    }
}
