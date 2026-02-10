package com.loft.demo.exposition;

import com.loft.demo.domain.Task;
import com.loft.demo.exposition.dto.TaskRequest;
import com.loft.demo.exposition.dto.TaskResponse;
import com.loft.demo.service.TaskService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class TaskController {

    private final TaskService taskService;
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/tasks")
    public ResponseEntity<TaskResponse> nouvelleTask(@RequestBody @Valid TaskRequest taskRequest){
        LOGGER.info("POST REQUEST ");
        return new ResponseEntity<>(taskService.createNewTask(taskRequest.toEntity()), HttpStatus.CREATED);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskResponse>> listerToutesLesTasks(){
        LOGGER.info("GET request - lister toutes les tasks");
        return new ResponseEntity<>(taskService.getAllTask(), HttpStatus.OK);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<TaskResponse> recupererUneTask(@PathVariable Long id){
        LOGGER.info("GET REQUEST - recuperer une task");
        var response = taskService.getTaskById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<TaskResponse> miseAJourTask(@PathVariable Long id, @RequestBody TaskRequest taskRequest){
        LOGGER.info("PUT REQUEST - mise à jour task");
        return new ResponseEntity<>(taskService.updateTask(id, taskRequest), HttpStatus.OK);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<String> supprimerTask(@PathVariable Long id){
        LOGGER.info("DELETE REQUEST ");
        taskService.deleteTask(id);
        return new ResponseEntity<>("File delete", HttpStatus.OK);
    }
}
