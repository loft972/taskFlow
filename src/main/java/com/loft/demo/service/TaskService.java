package com.loft.demo.service;

import com.loft.demo.domain.Task;
import com.loft.demo.exception.ResourceNotFoundException;
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
        var taskUpdate = taskRepository.findById(id);
        if(taskUpdate.isPresent()){
            taskUpdate.get().setTitle(title);
            return taskRepository.save(taskUpdate.get());
        } else {
            throw new ResourceNotFoundException("Task not FOund");
        }

    }

    public void deleteTask(Long id){
         taskRepository.deleteById(id);
    }

    public Task getTaskById(Task task){
        return taskRepository.findById(task.getId()).orElseThrow(ResourceNotFoundException::new);
    }

    public List<Task> getAllTask(){
        return taskRepository.findAll();
    }

}
