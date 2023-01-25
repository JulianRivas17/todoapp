package com.endpoints.todoapp.controller;

import com.endpoints.todoapp.persistence.entity.Task;
import com.endpoints.todoapp.persistence.entity.TaskStatus;
import com.endpoints.todoapp.service.TaskService;
import com.endpoints.todoapp.service.dto.TaskinDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task createTask(@RequestBody TaskinDTO taskinDTO) {
        return this.taskService.createTask(taskinDTO);
    }

    @GetMapping
    public List<Task> findAll(){
        return  this.taskService.finAll();
    }

    @GetMapping("/status/{status}")
    public List<Task> finAllbyStatus(@PathVariable("status")TaskStatus status){
        return  this.taskService.findAllByTaskStatus(status);
    }

    @PatchMapping("/mark_as_finished/{id}")
    public ResponseEntity<Void> markAsFinished(@PathVariable("id") Long id){
        this.taskService.updateTaskAsFinished(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
