package com.ss2.taskmanagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ss2.taskmanagement.models.Task;
import com.ss2.taskmanagement.repositories.TaskRepository;

@Service
public class TaskService {
    private TaskRepository taskRepository;
    private UserService userService;

    @Autowired 
    public TaskService(TaskRepository taskRepository, UserService userService){
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    public List<Task> findAllTasks(){
        return taskRepository.findAll();
    }

    public Task addTask(Task task){
        //Kiểm tra user có tồn tại không
        int id = task.getAssignedTo();
        if(id <= 0) return null;
        if(userService.findById(id) == null) return null;
        //Thêm task
        return taskRepository.addTask(task);
    }
}
