package com.ss2.taskmanagement.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ss2.taskmanagement.models.Task;
import com.ss2.taskmanagement.models.User;

@Repository 
public class TaskRepository {
    private List<Task> tasks = new ArrayList<>();
    
    @Autowired 
    public TaskRepository(UserRepository userRepository){
        List<User> users = userRepository.findAll();

        tasks.add(new Task(1, "Task 1", "Description 1", "High", users.get(0).getId()));
        tasks.add(new Task(2, "Task 2", "Description 2", "Medium", users.get(1).getId()));
        tasks.add(new Task(3, "Task 3", "Description 3", "Low", users.get(2).getId()));
    }

    public List<Task> findAll(){
        return tasks;
    }

    public Task addTask(Task task){
        tasks.add(task);
        return task;
    }

    public Task findById(int id){
        for(Task t:tasks){
            if(t.getId() == id){
                return t;
            }
        }
        return null;
    }

    public Task updateTask(int id, Task task){
        Task t = findById(id);
        if(t != null){
            t.setTitle(task.getTitle());
            t.setDescription(task.getDescription());
            t.setPriority(task.getPriority());
            t.setAssignedTo(task.getAssignedTo());
            return t;
        }
        return null;
    }

    public boolean deleteTask(int id){
        Task t = findById(id);
        if(t != null){
            tasks.remove(t);
            return true;
        }
        return false;
    }

    
}
