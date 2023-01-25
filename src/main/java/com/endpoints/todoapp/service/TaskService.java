package com.endpoints.todoapp.service;

import com.endpoints.todoapp.exceptions.ToDoExceptions;
import com.endpoints.todoapp.mapper.TaskinDTOtoTask;
import com.endpoints.todoapp.persistence.entity.Task;
import com.endpoints.todoapp.persistence.entity.TaskStatus;
import com.endpoints.todoapp.persistence.repository.TaskRepository;
import com.endpoints.todoapp.service.dto.TaskinDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private  final TaskRepository repository;
    private  final TaskinDTOtoTask mapper;

    public TaskService(TaskRepository repository, TaskinDTOtoTask mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Task createTask(TaskinDTO taskinDTO) {
        Task task = mapper.map(taskinDTO);
        return this.repository.save(task);
    }

    public List<Task> finAll(){
        return  this.repository.findAll();
    }

    public List<Task> findAllByTaskStatus(TaskStatus status){
        return  this.repository.findAllByTaskStatus(status);
    }

    @Transactional
    public void updateTaskAsFinished(Long id) {
        Optional<Task> optionalTask = this.repository.findById(id);
        if (optionalTask.isEmpty()){
            throw new ToDoExceptions("Task not found" , HttpStatus.NOT_FOUND);
        }

        this.repository.markTaskFinished(id);
    }

    public void deleteById(Long id) {
        Optional<Task> optionalTask = this.repository.findById(id);
        if (optionalTask.isEmpty()){
            throw new ToDoExceptions("Task not found" , HttpStatus.NOT_FOUND);
        }
        this.repository.deleteById(id);
    }

}
