package com.loft.demo.exposition.dto;

import com.loft.demo.domain.Task;
import com.loft.demo.domain.TaskPriority;
import com.loft.demo.domain.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record TaskRequest(

        @NotBlank @Size(min = 3, max = 100) String title,
        @Size(max = 500) String description,
        TaskStatus status,
        @NotNull TaskPriority priority,
        LocalDate dueDate
) {
    public static TaskRequest newTask(String title, String description, TaskPriority priority){
        return new TaskRequest(title, description, TaskStatus.TODO,priority, null);
    }

    public Task toEntity(){
        return new Task(this.title,this.description, this.status, this.priority, this.dueDate);
    }


}
