package com.loft.demo.repository;

import com.loft.demo.domain.Task;
import com.loft.demo.domain.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findByTitle(String title);
    List<Task> findByStatus(TaskStatus status);
}
