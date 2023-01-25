package com.endpoints.todoapp.persistence.repository;

import com.endpoints.todoapp.persistence.entity.Task;
import com.endpoints.todoapp.persistence.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    public List<Task> findAllByTaskStatus(TaskStatus taskStatus);

    @Modifying
    @Query(value = "UPDATE TASK SET FINISHED=true WHERE ID=:id ",nativeQuery = true)
    public void markTaskFinished(@Param("id") Long id);

}
