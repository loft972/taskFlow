package com.loft.demo.service;

import com.loft.demo.domain.Task;
import com.loft.demo.exception.ResourceNotFoundException;
import com.loft.demo.exposition.dto.TaskRequest;
import com.loft.demo.exposition.dto.TaskResponse;
import com.loft.demo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskResponse createNewTask(Task task){
         return this.toResponse(taskRepository.save(task));
    }

    public TaskResponse updateTask(Long id, TaskRequest taskRequest){
        var taskToUpdate = taskRepository.findById(id);
        if(taskToUpdate.isPresent()){
            taskToUpdate.get().setTitle(taskRequest.title());
            taskToUpdate.get().setStatus(taskRequest.status());
            taskToUpdate.get().setDescription(taskRequest.description());
            taskToUpdate.get().setUpdatedAt(LocalDateTime.now());
        } else {
            throw new ResourceNotFoundException("Il n'y a pas de task avec cette id pour la mise à jour");
        }
        return toResponse(taskRepository.save(taskToUpdate.get()));
    }

    public void deleteTask(Long id){
         taskRepository.deleteById(id);
    }

    public TaskResponse getTaskById(Long id){
        return toResponse(taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task doesn't exist")));
    }

    public List<TaskResponse> getAllTask(){
        return taskRepository.findAll().stream().map(this::toResponse).toList();
    }

    public TaskResponse toResponse(Task task){
        return new TaskResponse(task.getId(),
                task.getTitle(), task.getDescription(), task.getStatus(), task.getPriority(),
                task.getDueDate(), task.getCreatedAt(), task.getUpdatedAt());
    }

}
