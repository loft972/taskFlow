package com.loft.demo.exposition;

import com.loft.demo.domain.Task;
import com.loft.demo.service.TaskService;
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
    public ResponseEntity<Task> nouvelleTask(@RequestBody Task task){
        LOGGER.info("POST REQUEST ");
        return new ResponseEntity<>(taskService.createNewTask(task), HttpStatus.CREATED);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> listerToutesLesTasks(){
        LOGGER.info("GEt REQUEST");
        return new ResponseEntity<>(taskService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> miseAJourTask(@PathVariable Long id, @RequestParam String newTitle){
        LOGGER.info("PUT REQUEST - id : {}, newTitle : {}", id, newTitle);
        return new ResponseEntity(taskService.updateTask(id, newTitle), HttpStatus.OK);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<String> supprimerTaskk(@PathVariable Long id){
        LOGGER.info("DELETE REQUEST ");
        taskService.deleteTask(id);
        return new ResponseEntity("File delete", HttpStatus.OK);
    }
}
