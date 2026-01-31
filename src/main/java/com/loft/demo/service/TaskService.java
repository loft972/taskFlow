package com.loft.demo.service;

import com.loft.demo.domain.Task;
import com.loft.demo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createNewTask(Task task){
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, String title){
        var tasktoUpdate = taskRepository.findById(id);
        tasktoUpdate.get().setTitle(title);
        return taskRepository.save(tasktoUpdate.get());
    }

    public void deleteTask(Long id){
         taskRepository.deleteById(id);
    }

    public Task findTask(Task task){
        return taskRepository.findById(task.getId()).orElse(null);
    }

    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    public Task findByTitle(String title){
        return taskRepository.findTaskByTitle(title).orElse(null);
    }
}
